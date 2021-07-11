package module;

public class CalculatorRecImpl implements Calculator {
    @Override
    public long factorial(long num) {
//        long startTime = System.currentTimeMillis();

        try {
            if (num == 0)
                return 1;
            else
                return num * factorial(num - 1);
        } finally {
//            long endTime = System.currentTimeMillis();
//            System.out.printf("CalculatorRecImpl.factorial(%d) 실행 시간 = %d\n"
//                    , num, (endTime - startTime)
//            );
        }
    }
}
