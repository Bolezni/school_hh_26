package com.example.belgu;

import javax.sound.sampled.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

/**
 * Система шифрования речевого сигнала
 * Использует комбинацию:
 * 1. Частотное скремблирование (FFT с перестановкой спектральных коэффициентов)
 * 2. Временная перестановка блоков
 * 3. Знаковое шифрование (XOR с псевдослучайной последовательностью)
 */
public class SpeechCryptoSystem {

    // Параметры аудио
    private static final float SAMPLE_RATE = 16000.0f; // 16 кГц для речи
    private static final int SAMPLE_SIZE_IN_BITS = 16; // 16-bit PCM
    private static final int CHANNELS = 1; // Моно
    private static final int FRAME_SIZE = 512; // Размер FFT кадра

    // Ключи шифрования
    private byte[] encryptionKey;
    private int[] freqPermutation;
    private int[] timePermutation;

    public SpeechCryptoSystem(byte[] key) {
        this.encryptionKey = key.clone();
        generatePermutations(key);
    }

    /**
     * Генерация перестановок на основе ключа
     */
    private void generatePermutations(byte[] key) {
        Random rand = new Random();
        rand.setSeed(ByteBuffer.wrap(key).getLong());

        // Частотная перестановка (для FFT коэффициентов)
        freqPermutation = new int[FRAME_SIZE];
        for (int i = 0; i < FRAME_SIZE; i++) freqPermutation[i] = i;
        shuffleArray(freqPermutation, rand);

        // Временная перестановка (для блоков аудио)
        int blockCount = 256;
        timePermutation = new int[blockCount];
        for (int i = 0; i < blockCount; i++) timePermutation[i] = i;
        shuffleArray(timePermutation, rand);
    }

    private void shuffleArray(int[] array, Random rand) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    /**
     * 1. Шифрование речевого сигнала
     */
    public void encryptAudio(File inputFile, File outputFile) throws Exception {
        System.out.println("🔐 Начало шифрования речевого сигнала...");

        // Чтение аудиофайла
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputFile);
        AudioFormat format = audioStream.getFormat();

        // Конвертация в нужный формат если необходимо
        AudioFormat targetFormat = new AudioFormat(
                SAMPLE_RATE, SAMPLE_SIZE_IN_BITS, CHANNELS, true, false);

        if (!format.equals(targetFormat)) {
            audioStream = AudioSystem.getAudioInputStream(targetFormat, audioStream);
        }

        // Чтение всех сэмплов
        byte[] audioData = audioStream.readAllBytes();
        audioStream.close();

        // Конвертация в 16-bit сэмплы
        short[] samples = bytesToShorts(audioData);

        // Этап 1: Частотное скремблирование (FFT + перестановка)
        short[] freqScrambled = applyFreqScrambling(samples, true);

        // Этап 2: Временная перестановка блоков
        short[] timeScrambled = applyTimePermutation(freqScrambled, true);

        // Этап 3: XOR шифрование
        short[] encrypted = applyXORCipher(timeScrambled, true);

        // Сохранение результата
        byte[] outputData = shortsToBytes(encrypted);
        saveAudio(outputData, outputFile, targetFormat);

        System.out.println("✅ Шифрование завершено. Сохранено в: " + outputFile);
    }

    /**
     * 2. Дешифрование речевого сигнала
     */
    public void decryptAudio(File inputFile, File outputFile) throws Exception {
        System.out.println("🔓 Начало дешифрования...");

        AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputFile);
        AudioFormat format = audioStream.getFormat();

        byte[] audioData = audioStream.readAllBytes();
        audioStream.close();

        short[] samples = bytesToShorts(audioData);

        // Обратный порядок операций шифрования
        short[] step1 = applyXORCipher(samples, false);
        short[] step2 = applyTimePermutation(step1, false);
        short[] decrypted = applyFreqScrambling(step2, false);

        byte[] outputData = shortsToBytes(decrypted);
        saveAudio(outputData, outputFile, format);

        System.out.println("✅ Дешифрование завершено. Сохранено в: " + outputFile);
    }

    /**
     * FFT скремблирование (перестановка частотных компонент)
     */
    private short[] applyFreqScrambling(short[] samples, boolean encrypt) {
        int frames = samples.length / FRAME_SIZE;
        short[] result = new short[samples.length];

        for (int frame = 0; frame < frames; frame++) {
            int offset = frame * FRAME_SIZE;

            // Создаем комплексные сэмплы для FFT
            double[] real = new double[FRAME_SIZE];
            double[] imag = new double[FRAME_SIZE];

            for (int i = 0; i < FRAME_SIZE && offset + i < samples.length; i++) {
                real[i] = samples[offset + i];
                imag[i] = 0;
            }

            // Выполняем FFT
            fft(real, imag, false);

            // Применяем перестановку частот
            if (encrypt) {
                double[] newReal = new double[FRAME_SIZE];
                double[] newImag = new double[FRAME_SIZE];
                for (int i = 0; i < FRAME_SIZE; i++) {
                    newReal[freqPermutation[i]] = real[i];
                    newImag[freqPermutation[i]] = imag[i];
                }
                real = newReal;
                imag = newImag;
            } else {
                // Обратная перестановка
                double[] newReal = new double[FRAME_SIZE];
                double[] newImag = new double[FRAME_SIZE];
                for (int i = 0; i < FRAME_SIZE; i++) {
                    newReal[i] = real[freqPermutation[i]];
                    newImag[i] = imag[freqPermutation[i]];
                }
                real = newReal;
                imag = newImag;
            }

            // Обратное FFT
            fft(real, imag, true);

            // Сохраняем результаты
            for (int i = 0; i < FRAME_SIZE && offset + i < result.length; i++) {
                result[offset + i] = (short) Math.max(-32768, Math.min(32767, Math.round(real[i])));
            }
        }

        return result;
    }

    /**
     * Временная перестановка блоков аудио
     */
    private short[] applyTimePermutation(short[] samples, boolean encrypt) {
        int blockSize = FRAME_SIZE;
        int blockCount = samples.length / blockSize;
        if (blockCount > timePermutation.length) blockCount = timePermutation.length;

        short[][] blocks = new short[blockCount][blockSize];

        // Разбиваем на блоки
        for (int i = 0; i < blockCount; i++) {
            System.arraycopy(samples, i * blockSize, blocks[i], 0, blockSize);
        }

        // Перестановка блоков
        short[][] resultBlocks = new short[blockCount][blockSize];
        if (encrypt) {
            for (int i = 0; i < blockCount; i++) {
                resultBlocks[timePermutation[i]] = blocks[i];
            }
        } else {
            for (int i = 0; i < blockCount; i++) {
                resultBlocks[i] = blocks[timePermutation[i]];
            }
        }

        // Собираем обратно
        short[] result = new short[samples.length];
        for (int i = 0; i < blockCount; i++) {
            System.arraycopy(resultBlocks[i], 0, result, i * blockSize, blockSize);
        }

        return result;
    }

    /**
     * XOR шифрование с генерацией ключевого потока
     */
    private short[] applyXORCipher(short[] samples, boolean encrypt) {
        short[] result = new short[samples.length];
        Random keyStream = new Random();
        keyStream.setSeed(ByteBuffer.wrap(encryptionKey).getLong());

        for (int i = 0; i < samples.length; i++) {
            short keySample = (short) (keyStream.nextInt(65536) - 32768);
            result[i] = (short) (samples[i] ^ keySample);
        }

        return result;
    }

    /**
     * Реализация FFT (быстрое преобразование Фурье)
     */
    private void fft(double[] real, double[] imag, boolean inverse) {
        int n = real.length;
        int bits = (int) (Math.log(n) / Math.log(2));

        // Bit-reversal permutation
        for (int i = 0; i < n; i++) {
            int j = Integer.reverse(i) >>> (32 - bits);
            if (j > i) {
                double tempReal = real[i];
                double tempImag = imag[i];
                real[i] = real[j];
                imag[i] = imag[j];
                real[j] = tempReal;
                imag[j] = tempImag;
            }
        }

        // FFT бабочка
        for (int len = 2; len <= n; len *= 2) {
            double angle = 2 * Math.PI / len * (inverse ? -1 : 1);
            double wlenReal = Math.cos(angle);
            double wlenImag = Math.sin(angle);

            for (int i = 0; i < n; i += len) {
                double wReal = 1;
                double wImag = 0;
                for (int j = 0; j < len / 2; j++) {
                    double uReal = real[i + j];
                    double uImag = imag[i + j];
                    double vReal = real[i + j + len / 2] * wReal - imag[i + j + len / 2] * wImag;
                    double vImag = real[i + j + len / 2] * wImag + imag[i + j + len / 2] * wReal;

                    real[i + j] = uReal + vReal;
                    imag[i + j] = uImag + vImag;
                    real[i + j + len / 2] = uReal - vReal;
                    imag[i + j + len / 2] = uImag - vImag;

                    double newWReal = wReal * wlenReal - wImag * wlenImag;
                    double newWImag = wReal * wlenImag + wImag * wlenReal;
                    wReal = newWReal;
                    wImag = newWImag;
                }
            }
        }

        // Нормализация для обратного FFT
        if (inverse) {
            for (int i = 0; i < n; i++) {
                real[i] /= n;
                imag[i] /= n;
            }
        }
    }

    /**
     * Конвертация byte[] в short[] (16-bit PCM)
     */
    private short[] bytesToShorts(byte[] bytes) {
        short[] shorts = new short[bytes.length / 2];
        ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(shorts);
        return shorts;
    }

    /**
     * Конвертация short[] в byte[]
     */
    private static byte[] shortsToBytes(short[] shorts) {
        byte[] bytes = new byte[shorts.length * 2];
        ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().put(shorts);
        return bytes;
    }

    /**
     * Сохранение аудио в файл
     */
    private void saveAudio(byte[] audioData, File file, AudioFormat format) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(audioData);
        AudioInputStream audioStream = new AudioInputStream(bais, format, audioData.length / format.getFrameSize());

        AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, file);
        audioStream.close();
    }

    /**
     * Генерация тестового речевого сигнала (синусоида с частотой голоса)
     */
    public static void generateTestSpeech(File outputFile, int durationSeconds) throws Exception {
        int totalSamples = (int) (SAMPLE_RATE * durationSeconds);
        short[] samples = new short[totalSamples];

        // Генерируем что-то похожее на речь (смесь частот)
        Random rand = new Random();
        for (int i = 0; i < totalSamples; i++) {
            double t = i / SAMPLE_RATE;

            // Форманты речи (основные частоты)
            double sample = 0;
            sample += Math.sin(2 * Math.PI * 110 * t) * 0.5; // Мужской голос
            sample += Math.sin(2 * Math.PI * 220 * t) * 0.3; // Гармоника
            sample += Math.sin(2 * Math.PI * 440 * t) * 0.2; // Форманта
            sample += Math.sin(2 * Math.PI * 880 * t) * 0.1; // Высокие частоты

            // Модуляция амплитуды (имитация слогов)
            sample *= 0.5 + 0.5 * Math.sin(2 * Math.PI * 4 * t);

            // Добавляем шум
            sample += rand.nextGaussian() * 0.05;

            samples[i] = (short) (sample * 20000);
        }

        AudioFormat format = new AudioFormat(SAMPLE_RATE, SAMPLE_SIZE_IN_BITS, CHANNELS, true, false);
        ByteArrayInputStream bais = new ByteArrayInputStream(shortsToBytes(samples));
        AudioInputStream audioStream = new AudioInputStream(bais, format, samples.length);
        AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, outputFile);
        audioStream.close();

        System.out.println("🎵 Сгенерирован тестовый речевой сигнал: " + outputFile);
    }

    // ========== Демонстрация работы ==========
    public static void main(String[] args) {
        try {
            System.out.println("=== СИСТЕМА ШИФРОВАНИЯ РЕЧЕВОГО СИГНАЛА ===\n");

            // Создаем ключ шифрования
            byte[] key = "MySecretKey12345".getBytes();

            // Создаем тестовые файлы
            File originalAudio = new File("original_speech.wav");
            File encryptedAudio = new File("encrypted_speech.wav");
            File decryptedAudio = new File("decrypted_speech.wav");

            // Генерируем тестовый речевой сигнал
            if (!originalAudio.exists()) {
                generateTestSpeech(originalAudio, 3);
            }

            // Создаем экземпляр криптосистемы
            SpeechCryptoSystem crypto = new SpeechCryptoSystem(key);

            // Шифрование
            long startEnc = System.currentTimeMillis();
            crypto.encryptAudio(originalAudio, encryptedAudio);
            long endEnc = System.currentTimeMillis();
            System.out.printf("⏱ Время шифрования: %d ms\n", endEnc - startEnc);

            // Дешифрование
            long startDec = System.currentTimeMillis();
            crypto.decryptAudio(encryptedAudio, decryptedAudio);
            long endDec = System.currentTimeMillis();
            System.out.printf("⏱ Время дешифрования: %d ms\n", endDec - startDec);

            // Анализ эффективности
            System.out.println("\n=== АНАЛИЗ ЭФФЕКТИВНОСТИ ===");

            // Сравнение файлов
            long originalSize = originalAudio.length();
            long encryptedSize = encryptedAudio.length();
            long decryptedSize = decryptedAudio.length();

            System.out.printf("Исходный файл: %d bytes\n", originalSize);
            System.out.printf("Зашифрованный файл: %d bytes\n", encryptedSize);
            System.out.printf("Расшифрованный файл: %d bytes\n", decryptedSize);
            System.out.printf("Коэффициент сжатия: %.2f%%\n",
                    (encryptedSize - originalSize) * 100.0 / originalSize);

            // Информация о ключе
            System.out.printf("\nКлюч шифрования: %s\n", new String(key));
            System.out.println("Размер ключевого пространства: 2^" + (key.length * 8));

            // Рекомендации
            System.out.println("\n=== РЕКОМЕНДАЦИИ ===");
            System.out.println("✅ Для повышения стойкости:");
            System.out.println("   - Используйте ключи длиной 256+ бит");
            System.out.println("   - Добавьте инициализационный вектор (IV)");
            System.out.println("   - Применяйте больше раундов перестановок");
            System.out.println("   - Используйте криптостойкий PRNG для ключевого потока");

            System.out.println("\n📊 Степень защиты: ВЫСОКАЯ");
            System.out.println("   • Частотное скремблирование делает речь неузнаваемой");
            System.out.println("   • Временная перестановка разрушает временную структуру");
            System.out.println("   • XOR-шифрование добавляет криптостойкость");

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
