package info.henrysson.advent_bonus.model;

public enum City {
    GOTHENBURG("Göteborg", "https://mat.se/api/product/getCategoryTree");

    City(final String name, final String url) {
        this.name = name;
        this.url = url;
    }

    private final String name;
    private final String url;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
