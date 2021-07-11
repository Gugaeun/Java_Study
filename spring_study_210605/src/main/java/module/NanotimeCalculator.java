package module;

public class NanotimeCalculator implements Calculator {
    private Calculator delegate;
    public NanotimeCalculator(Calculator delegate) {
        this.delegate = delegate;
    }

    @Override
    public long factorial(long num) {
        long startTime = System.nanoTime();
        long result = delegate.factorial(num);
        long endTime = System.nanoTime();
        System.out.printf("NanotimeCalculator.factorial(" + num +") 실행 시간 = %d\n", (endTime - startTime));

        return result;
    }
}
