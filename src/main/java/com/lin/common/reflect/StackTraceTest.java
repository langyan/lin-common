/**
 * 
 */
package com.lin.common.reflect;

/**
 * @author michael
 *
 */
public class StackTraceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StackTraceTest test=new StackTraceTest();
		test.methodA();

	}
	
	private void methodA(){
        System.out.println("------进入methodA----------");
        methodB();
    }

    private void methodB(){
        System.out.println("------进入methodB----------");
        StackTraceElement elements[] = Thread.currentThread().getStackTrace();
        for (int i = 0; i < elements.length; i++) {
            StackTraceElement stackTraceElement=elements[i];
            String className=stackTraceElement.getClassName();
            String methodName=stackTraceElement.getMethodName();
            String fileName=stackTraceElement.getFileName();
            int lineNumber=stackTraceElement.getLineNumber();
            System.out.println("StackTraceElement数组下标 i="+i+",fileName="
                    +fileName+",className="+className+",methodName="+methodName+",lineNumber="+lineNumber);
        }
    }
	
	private Class<?> deduceMainApplicationClass() {
		try {
			StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();
			for (StackTraceElement stackTraceElement : stackTrace) {
				if ("main".equals(stackTraceElement.getMethodName())) {
					return Class.forName(stackTraceElement.getClassName());
				}
			}
		}
		catch (ClassNotFoundException ex) {
			// Swallow and continue
		}
		return null;
	}
	
	 public static void onResume() {
	        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
	        boolean result = false;
	        for (StackTraceElement stackTraceElement : stackTrace) {
	            String methodName = stackTraceElement.getMethodName();
	            String className = stackTraceElement.getClassName();
	            try {
	                boolean assignableFromClass = Class.forName(className).isAssignableFrom(Object.class);
	                if (assignableFromClass && "onResume".equals(methodName)) {
	                    result = true;
	                    break;
	                }
	            } catch (ClassNotFoundException e) {

	            }
	        }
	        if (!result)
	            throw new RuntimeException("PVSdk.onResume must in Activity.onResume");
	    }

}
