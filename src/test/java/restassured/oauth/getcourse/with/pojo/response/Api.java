package restassured.oauth.getcourse.with.pojo.response;

public class Api {
    String courseTitle;
    String price;

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Api { " +
                "courseTitle='" + courseTitle + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
