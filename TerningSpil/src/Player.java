
    public class Player {
        private String name;
        private int point;


        public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
    public void addPoint(int point) {
        this.point += point;
    }

    public String getName() {
        return name;
    }


    public Player(String name){
        this.name=name;
        this.point=0;
    }
}



