package place.dto;

import place.dto.kakao.format.Document;

import java.util.List;

public class PlaceList {
    private int currentPage;
    private int totalPage;
    private int size;
    private List<Document> documents;

    public PlaceList(int currentPage, int totalPage, int size, List<Document> documents) {
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.size = size;
        this.documents = documents;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getSize() {
        return size;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    @Override
    public String toString() {
        return "PlaceList{" +
                "currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", size=" + size +
                ", documents=" + documents +
                '}';
    }
}
