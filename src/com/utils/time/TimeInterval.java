package com.utils.time;

import java.sql.Timestamp;

public class TimeInterval {
    public final Timestamp start;
    public final Timestamp end;

    @Override
    public String toString() {
	return "TimeInterval [start=" + start + ", end=" + end + "]";
    }

    public TimeInterval(Timestamp start, Timestamp end) {
	this.start = start;
	this.end = end;
    }

    public TimeInterval() {
	this.start = null;
	this.end = null;
    }

    /**
     * 判断给定的time是否在范围内（start<=time<end）
     * @param time
     * @return
     */
    public boolean isIn(Timestamp time) {
	if (time == null) {
	    return false;
	}
	long lstart = start == null ? 0 : start.getTime();
	long lend = end == null ? 0 : end.getTime();
	long lnow = time.getTime();
	if (start == null && end == null) {
	    return true;
	} else if (start != null && end == null) {
	    return lnow >= lstart ? true : false;
	} else if (start == null && end != null) {
	    return lnow < lend ? true : false;
	} else {
	    return (lnow < lend && lnow >= lstart) ? true : false;
	}
    }
}
