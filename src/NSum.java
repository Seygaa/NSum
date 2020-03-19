//Joanna Bańkowska

public class NSum {
    private static final int MAX_SIZE = 100000;
    private static int label = 1;
    private static int[] results;
    private static FunctionContext functionContext;

    static int power(int num, int n) {
        if (n == 0)
            return 1;
        else if (n % 2 == 0)
            return power(num, n / 2) * power(num, n / 2);
        else
            return num * power(num, n / 2) * power(num, n / 2);
    }

    static int[] pushToArray(int[] array, int element) {
        int[] res = new int[array.length + 1];
        System.arraycopy(array, 0, res, 0, array.length);
        res[array.length] = element;
        return res;
    }

/////////////////////////////////////REKURYNECYJNIE/////////////////////////////////////

    public static int[] findNComponentsRecAux(int x, int n, int currentNum, int currentSum) {
        int p = power(currentNum, n);// 2
        int[] results = new int[]{};
        if (p + currentSum == x) {
            return new int[]{currentNum};
        }
        if (p + currentSum < x) {
            results = findNComponentsRecAux(x, n, currentNum + 1, p + currentSum);// 3
            if (results.length > 0) { // 4
                return pushToArray(results, currentNum);
            }
            results = findNComponentsRecAux(x, n, currentNum + 1, currentSum);// 5
        }
        return results; // 6
    }

    public static int[] findNComponentsRec(int x, int n) {
        //1
        return findNComponentsRecAux(x, n, 1, 0);
        //7
    }

/////////////////////////////////////ITERACYJNIE/////////////////////////////////////

    public static boolean step(Stack stack, int x, int n) {
        switch (label) {
            case 1:
                functionContext = new FunctionContext(1, 0, 7);
                label = 2;
                stack.push(functionContext);
                break;
            case 2:
                functionContext = stack.top();
                int p = power(functionContext.currentNum, n);
                if (p + functionContext.currentSum == x) {
                    results = new int[]{functionContext.currentNum};
                    label = 6;
                } else if (p + functionContext.currentSum > x) {
                    label = 6;
                    results = new int[]{};
                } else {
                    label = 3;
                }
                break;
            case 3:
                p = power(functionContext.currentNum, n);
                FunctionContext newFunctionContext = new FunctionContext(functionContext.currentNum + 1, p + functionContext.currentSum, 4);
                stack.push(newFunctionContext);
                label = 2;
                break;
            case 4:
                functionContext = stack.top();

                if (results.length > 0) { // 4
                    results = pushToArray(results, functionContext.currentNum);
                    label = 6;
                } else {
                    label = 5;
                }
                break;
            case 5:
                newFunctionContext = new FunctionContext(functionContext.currentNum + 1, functionContext.currentSum, 6);
                stack.push(newFunctionContext);
                label = 2;
                break;
            case 6:
                functionContext = stack.top();
                label = functionContext.addr;
                stack.pop();
                break;
            case 7:
                return true;

        }
        return false;
    }

    public static int[] findNComponentsIter(int x, int n) {
        Stack stack = new Stack(MAX_SIZE);
        results = new int[]{};
        label = 1;

        while (!step(stack, x, n)) ;

        return results;
    }
}