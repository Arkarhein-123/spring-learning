package org.example.springaop.logger;

public class PerformanceLogger {

    public PerformanceLoggerInfo startTime(String name){
        return new PerformanceLoggerInfo(name,
                System.currentTimeMillis());
    }
    public void endTime(PerformanceLoggerInfo performanceLoggerInfo){
        long startTime= performanceLoggerInfo.getStartTime();
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;
        System.out.println("%s method take times %s milliseconds."
                .formatted(performanceLoggerInfo.methodName,elapsedTime));
    }

    public static class PerformanceLoggerInfo{
        private String methodName;
        private long startTime;
        public PerformanceLoggerInfo(String methodName, long startTime) {
            this.methodName = methodName;
            this.startTime = startTime;
        }
        public String getMethodName() {
            return methodName;
        }
        public long getStartTime() {
            return startTime;
        }
    }
}