package spring;

public class VersionPrinter {
    private int majorVersion;
    private int middleVersion;
    private int minorVersion;

    public void print() {
        System.out.printf("이 프로그램의 버전은 %d.%d.%d 입니다.\n\n", majorVersion, middleVersion, minorVersion);
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public void setMiddleVersion(int middleVersion) {
        this.middleVersion = middleVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }
}

