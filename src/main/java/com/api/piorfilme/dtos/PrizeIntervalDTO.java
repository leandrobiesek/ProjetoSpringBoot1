package com.api.piorfilme.dtos;

public class PrizeIntervalDTO {

    private String producer;

    private Long interval;

    private Long previousWin;

    private Long followingWin;

    public PrizeIntervalDTO() {}

    public PrizeIntervalDTO(String producer, Long interval, Long previousWin, Long followingWin) {
        this.producer = producer;
        this.interval = interval;
        this.previousWin = previousWin;
        this.followingWin = followingWin;
    }


    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Long getInterval() {
        return interval;
    }

    public void setInterval(Long interval) {
        this.interval = interval;
    }

    public Long getPreviousWin() {
        return previousWin;
    }

    public void setPreviousWin(Long previousWin) {
        this.previousWin = previousWin;
    }

    public Long getFollowingWin() {
        return followingWin;
    }

    public void setFollowingWin(Long followingWin) {
        this.followingWin = followingWin;
    }
}
