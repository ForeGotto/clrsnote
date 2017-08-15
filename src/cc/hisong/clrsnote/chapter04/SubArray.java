package cc.hisong.clrsnote.chapter04;

import org.jetbrains.annotations.NotNull;

/**
 * denote a sub array
 * store start index, end index and sum of a sub array
 */
public class SubArray implements Comparable {
    int from;
    int to;
    long sum;

    public SubArray(int from, int to, int sum) {
        this.from = from;
        this.to = to;
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "SubArray{" +
                "from=" + from +
                ", to=" + to +
                ", sum=" + sum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubArray)) return false;

        SubArray subArray = (SubArray) o;

        if (getFrom() != subArray.getFrom()) return false;
        if (getTo() != subArray.getTo()) return false;
        return getSum() == subArray.getSum();
    }

    @Override
    public int hashCode() {
        int result = getFrom();
        result = 31 * result + getTo();
        result = 31 * result + (int) (getSum() ^ (getSum() >>> 32));
        return result;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    @Override
    public int compareTo(@NotNull Object o) {
        SubArray oSubArray = (SubArray) o;
        if (sum < oSubArray.sum) {
            return -1;
        }
        if (sum > oSubArray.sum) {
            return 1;
        }
        return 0;
    }
}
