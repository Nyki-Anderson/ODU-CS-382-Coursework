public class RuntimeErrors {
    public void doSomething(String s)
    {
        System.out.println("in doSomething");
        String[] array = new String[10];
        array[0] = "";
        for (int i=1; i<=10; ++i) {
            array[i] = array[i-1] + i;
            System.out.println("" + i + ": " + array[i]);
            int len1 = array[i-1].length();
            int len2 = array[i].length();
            double ratio = ((double)len2) / len1;
            System.out.println("This is " + ratio + " times larger.");
        }
    }

    public static void main (String[] args)
    {
        System.out.println("main has started");
        new RuntimeErrors().doSomething(null);
        System.out.println("returned to main");
    }
}
