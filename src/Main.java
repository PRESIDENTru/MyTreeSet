public class Main {
    public static void main(String[] args) {
        MyTreeSet<Integer> s = new MyTreeSet<>();

        s.add(70);
        s.add(50);
        s.add(20);
        s.add(40);
        s.remove(50);
        s.add(40);


        s.printTree();
        System.out.println(s.contains(0));
    }
}