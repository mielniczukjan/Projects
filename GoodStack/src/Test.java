public class Test {

    public static void main(String ...args) {
        GoodStack goodStack = new GoodStack();

        goodStack.push("Miroslaw");

        System.out.println(goodStack.size());
        System.out.println(goodStack.peek());

        goodStack.push("Bogusław");
        goodStack.push("Ignacy");
        goodStack.push("Pawel");
        goodStack.push("Krzysztof");
        goodStack.push("Bartosz");

        goodStack.print();

        System.out.println(goodStack.peek());
        System.out.println("Czy posiada Miroslawa : " + goodStack.contains("Miroslaw"));
        System.out.println("Jaki jest indeks Miroslawa " + goodStack.indexOf("Miroslaw"));
        System.out.println("Jaki jest indeks Krzysztofa " + goodStack.indexOf("Krzysztof"));
        System.out.println(goodStack.pop());

        goodStack.clear();

        System.out.println(goodStack.indexOf("Miroslaw"));
    }
}
