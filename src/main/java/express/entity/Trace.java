package express.entity;

/**
 *
 * @author HC
 * 快递路径实体类
 */
public class Trace {
//    某条路径的时间
    private String time;
//    某条路径的获得的顺序，用于多线程处理后的排序
    private int sort;
//    某条路径的的具体信息
    private String location;

    @Override
    public String toString() {
        return "Trace{" +
                "time='" + time + '\'' +
                ", sort=" + sort +
                ", location='" + location + '\'' +
                ", lng='" + lng + '\'' +
                ", lat='" + lat + '\'' +
                '}';
    }

    //    某条路径的位置地理坐标
//    经度
    private String lng;
//    纬度
    private String lat;

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
