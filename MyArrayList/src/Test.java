public class Test {
    public static void main(String... args) {
       MyArrayList myNewArrayList = new MyArrayList();

        System.out.println(myNewArrayList.isEmpty());

        myNewArrayList.add(3,"Testowy 1");
        myNewArrayList.show();
        myNewArrayList.add(6,"Testowy 2");
        System.out.println();
        System.out.println(myNewArrayList.indexOf("Testowy 2"));

        myNewArrayList.show();

        System.out.println();
        System.out.println(myNewArrayList.get(6));
        System.out.println(myNewArrayList.get(3));

        System.out.println(myNewArrayList.remove(3));

        myNewArrayList.set(1,"Testowy 3");

        System.out.println(myNewArrayList.indexOf("Testowy 2"));

        System.out.println(myNewArrayList.contains("Testowy 2"));

        System.out.println(myNewArrayList.size());
        myNewArrayList.clear();
        System.out.println(myNewArrayList.size());
        System.out.println(myNewArrayList.isEmpty());
    }
}
