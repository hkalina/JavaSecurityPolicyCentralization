class Program {
    public static void main(String[] args) throws Exception {
        DatabazeVSouboru databaze = new DatabazeVSouboru();
        databaze.ulozZaznam("Lucka","12345");
        System.out.println(databaze.nactiZaznam("Lucka"));
    }
}
