package module;

public class CalculatorImpl implements Calculator {
    @Override
    public long factorial(long num) {
//        long startTime = System.currentTimeMillis();

        long result = 1;
        for(long i = 1; i <= num; i++) {
            result = result * i;
        }

//        long endTime = System.currentTimeMillis();;
//        System.out.printf("CalculatorImpl.factorial(%d) 실행 시간 = %d\n"
//                , num, (endTime - startTime)
//        );

        return result;
    }
}
