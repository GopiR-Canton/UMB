package edu.umb.cs210.p1;

import edu.princeton.cs.algs4.In;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.fail;

public final class TestUtils {
    private static String packageName;
    private TestUtils() { /* no-op */ }

    public static void setPackageName(String newPackageName) {
        packageName = newPackageName+".";
    }

    /* ********************************************************************** */
    /* ************************** Helper Methods **************************** */
    /* ********************************************************************** */

    /**
     * Tests the given method in the given class using th given arguments.
     * Calls assertEquals() on the return from that method.
     * @param className the name of the class with the method under test
     * @param methodName the name of the method under test
     * @param methodParams the parameters for that method
     * @param methodArgs the arguments to that method
     * @param msg the message to give the user if assertion fails
     */
    public static void testMethod(String className, String methodName, Class<?>[] methodParams, Object[] methodArgs, String msg) {
        Object expect = callMethod("Ref"+className, methodName, methodParams, methodArgs);
        Object actual = callMethod(      className, methodName, methodParams, methodArgs);
        if (expect.getClass().isArray()) {
            assertArrayEquals((Object[])expect, (Object[])actual, msg);
        } else {
            assertEquals(expect, actual, msg);
        }
    }

    /**
     * Gets the root cause of a thrown exception.
     * Credit to user Legna from StackOverflow.
     *
     * @param e the exception you want the root cause for
     * @return the root cause of e
     */
    private static Throwable getRootCause(Throwable e) {
        Throwable cause = null;
        Throwable result = e;
        int i = 1;
        while(null != (cause = result.getCause())  && (result != cause) ) {
            result = cause;
            i++;
        }
        return result;
    }

    /**
     * Gets the Class object associated with the given class name. This
     * method serves to test whether a className.java file was submitted
     * and will cause the calling test to fail with an appropriate message.
     *
     * @param className the name of the class to create a Class object for
     * @return the Class object associated with className if available
     */
    public static Class<?> getClass(String className) {
        String msg;
        try {
            return Class.forName(packageName + className);
        } catch (ClassNotFoundException e) {
            String errorClass = e.getMessage().substring(packageName.length());
            String realClass = className.contains("Ref") ? className.substring(2) : className;
            if (errorClass.equals(realClass)) {
                String errorMessage = new In("out/"+errorClass+".java.err").readLine();
                msg = String.format("Missing java file or compile error. First line of message:\n%s", errorMessage);
            } else if (errorClass.equals("Ref" + realClass)) {
                msg = "Autograder error(Ref: ClassNotFoundException). Please report to Professor / TA.";
            } else {
                msg = "Autograder error(Oth: ClassNotFoundException). Please report to Professor / TA.";
            }
        }
        fail(msg);
        return null;
    }

    /**
     * Instantiates an object of type className with the given
     * constructorParams using the given constructorArgs.
     *
     * NOTE: If className's constructor requires arguments, you will need to
     * use one of the below overloaded getClassInstance methods.
     *
     * @param className the name of the class to be instantiated
     * @return an instance of type className
     */
    public static Object getClassInstance(String className) {
        int timeout = 30;
        return getClassInstance(className, timeout);
    }

    /**
     * Instantiates an object of type className with the given
     * constructorParams using the given constructorArgs.
     *
     * NOTE: If className's constructor requires arguments, you will need to
     * use one of the below overloaded getClassInstance methods.
     *
     * @param className the name of the class to be instantiated
     * @param timeout how long (seconds) constructor should run before timeout
     * @return an instance of type className
     */
    public static Object getClassInstance(String className, int timeout) {
        Class<?>[] constructorParams = new Class<?>[0];
        Object[] constructorArgs = new Object[0];
        return getClassInstance(className, constructorParams, constructorArgs, timeout);
    }
    /**
     * Instantiates an object of type className with the given
     * constructorParams using the given constructorArgs.
     *
     * @param className the name of the class to be instantiated
     * @param constructorParams the type parameters of the constructor
     * @param constructorArgs the arguments to the constructor
     * @return an instance of type className
     */
    public static Object getClassInstance(String className, Class<?>[] constructorParams, Object[] constructorArgs) {
        int timeout = 30;
        return getClassInstance(className, constructorParams, constructorArgs, timeout);
    }

    /**
     * Instantiates an object of type className with the given
     * constructorParams using the given constructorArgs.
     *
     * NOTE: This method is the end point of the chain of overloaded methods
     * that create a class instance.
     *
     * TODO: Wrap the call to newInstance in a call to assertTimeoutPreemptively
     *
     * @param className the name of the class to be instantiated
     * @param constructorParams the type parameters of the constructor
     * @param constructorArgs the arguments to the constructor
     * @param timeout how long (seconds) constructor should run before timeout
     * @return an instance of type className
     */
    public static Object getClassInstance(String className, Class<?>[] constructorParams, Object[] constructorArgs, int timeout) {
        String msg;
        try {
            return Objects.requireNonNull(getClass(className)).getDeclaredConstructor(constructorParams).newInstance(constructorArgs);
        } catch (NoSuchMethodException e) {
            System.err.println(e.getMessage());
            String errorClass = e.getMessage().substring(packageName.length());
            errorClass = errorClass.substring(0, errorClass.indexOf('.'));
            String realClass = className.contains("Ref") ? className.substring(3) : className;
            String expectedArgs = e.getMessage().substring(e.getMessage().indexOf('('));

            if (errorClass.equals(realClass)) {
                msg = String.format("Missing expected constructor in %s.java. Constructor should take args %s.", realClass, expectedArgs);
            } else if (errorClass.equals("Ref" + realClass)) {
                msg = "Autograder error(Ref NoSuchMethodException). Please report to Professor / TA.";
            } else {
                msg = "Autograder error(Other NoSuchMethodException). Please report to Professor / TA.";
            }

        } catch (InvocationTargetException e) {
            msg = "Constructor has thrown an exception. Please report this error to the professor or TA.";
            Throwable rootCause = getRootCause(e);
            if (rootCause instanceof IndexOutOfBoundsException) {
                throw (IndexOutOfBoundsException) rootCause;
            } else if (rootCause instanceof IllegalArgumentException) {
                throw (IllegalArgumentException) rootCause;
            }
        } catch (IllegalArgumentException e) {
            msg = "Could not create class instance. Please report this error to the professor or TA.";
        } catch (InstantiationException | IllegalAccessException e) {
            msg = "An unexpected exception was thrown. Please report this error to the professor or TA.";
        }
        fail(msg);
        return null;
    }

    /**
     * Attempts to create and return a Method object by the given methodName
     * and with the given methodParams from the className class. This method
     * used to get a method that should be invoked manually.
     *
     * NOTE: if the method signature includes type parameters,
     * you must use one of the overloaded getMethod functions.
     *
     * @param className the name of the class with a method to be returned
     * @param methodName the name of the method to be returned
     * @return the method object by the given methodName
     */
    public static Method getMethod(String className, String methodName) {
        Class<?>[] methodParams = new Class<?>[0];
        return getMethod(Objects.requireNonNull(getClass(className)), methodName, methodParams);
    }

    /**
     * Attempts to create and return a Method object by the given methodName
     * and with the given methodParams from the className class. This method
     * used to get a method that should be invoked manually.
     *
     * @param className the name of the class with a method to be returned
     * @param methodName the name of the method to be returned
     * @param methodParams the parameters for the given method
     * @return the method object by the given methodName
     */
    public static Method getMethod(String className, String methodName, Class<?>[] methodParams) {
        Class<?> theClass = Objects.requireNonNull(getClass(className));
        return getMethod(theClass, methodName, methodParams);
    }

    /**
     * Attempts to create and return a Method object by the given methodName
     * and with the given methodParams from the given Class object. This method
     * used to get a method that should be invoked manually.
     *
     * NOTE: if the method signature includes type parameters,
     * you must use one of the overloaded getMethod functions.
     *
     * @param theClass the Class object with a method to be returned
     * @param methodName the name of the method to be returned
     * @return the method object by the given methodName
     */
    public static Method getMethod(Class<?> theClass, String methodName) {
        Class<?>[] methodParams = new Class<?>[0];
        return getMethod(theClass, methodName, methodParams);
    }

    /**
     * Attempts to create and return a Method object by the given methodName
     * and with the given methodParams from the given Class object. This method
     * used to get a method that should be invoked manually.
     *
     * NOTE: This method is the end point of the chain of overloaded methods
     * that return a Method object.
     *
     * @param theClass the Class object with a method to be returned
     * @param methodName the name of the method to be returned
     * @param methodParams the parameters for the given method
     * @return the method object by the given methodName
     */
    public static Method getMethod(Class<?> theClass, String methodName, Class<?>[] methodParams) {
        String msg;
        try {
            return theClass.getDeclaredMethod(methodName, methodParams);
        } catch (NoSuchMethodException e) {
            System.err.println("getMethod: " + e.getMessage());
            String errorClass = e.getMessage().substring(packageName.length());
            String errorMethod = errorClass.substring(errorClass.indexOf('.')+1);
            errorClass = errorClass.substring(0, errorClass.indexOf('.'));

            String className = theClass.getName().substring(packageName.length());
            String realClass = className.contains("Ref") ? className.substring(3) : className;

            if (errorClass.equals(realClass)) {
                msg = String.format("Method %s not found.", errorMethod);
            } else if (errorClass.equals("Ref" + realClass)) {
                msg = "Autograder error(Ref NoSuchMethodException(getMethod)). Please report to Professor / TA.";
            } else {
                msg = "Autograder error(Other NoSuchMethodException(getMethod)). Please report to Professor / TA.";
            }
        }
        fail(msg);
        return null;
    }

    /**
     * Attempts to call the specified method using the given arguments. This
     * method is used to get what is returned from the method to test against
     * the project solution.
     *
     * NOTE: if the class constructor or method signature requires arguments,
     * you must use one of the overloaded callMethod functions.
     *
     * @param className the name of the class
     * @param methodName the name of the method to be called
     * @return whatever is returned by the method, if calling it was successful
     */
    public static Object callMethod(String className, String methodName) {
        int timeout = 30;
        return callMethod(className, methodName, timeout);
    }

    /**
     * Attempts to call the specified method using the given arguments. This
     * method is used to get what is returned from the method to test against
     * the project solution.
     *
     * NOTE: if the class constructor or method signature requires arguments,
     * you must use one of the overloaded callMethod functions.
     *
     * @param className the name of the class
     * @param methodName the name of the method to be called
     * @param timeout how long (in seconds) the method should run before timeout
     * @return whatever is returned by the method, if calling it was successful
     */
    public static Object callMethod(String className, String methodName, int timeout) {
        Class<?>[] constructorParams = new Class<?>[0];
        Object[] constructorArgs = new Object[0];
        Class<?>[] methodParams = new Class<?>[0];
        Object[] methodArgs = new Object[0];
        return callMethod(className, constructorParams, constructorArgs,
                methodName, methodParams, methodArgs, timeout);
    }

    /**
     * Attempts to call the specified method using the given arguments. This
     * method is used to get what is returned from the method to test against
     * the project solution.
     *
     * NOTE: if the method signature requires arguments,
     * you must use one of the overloaded callMethod functions.
     *
     * @param className the name of the class
     * @param constructorParams the parameters for the class constructor
     * @param constructorArgs the arguments for the class constructor
     * @param methodName the name of the method to be called
     * @return whatever is returned by the method, if calling it was successful
     */
    public static Object callMethod(String className, Class<?>[] constructorParams,
                              Object[] constructorArgs, String methodName) {
        int timeout = 30;
        return callMethod(className, constructorParams, constructorArgs, methodName, timeout);
    }

    /**
     * Attempts to call the specified method using the given arguments. This
     * method is used to get what is returned from the method to test against
     * the project solution.
     *
     * NOTE: if the method signature requires arguments,
     * you must use one of the overloaded callMethod functions.
     *
     * @param className the name of the class
     * @param constructorParams the parameters for the class constructor
     * @param constructorArgs the arguments for the class constructor
     * @param methodName the name of the method to be called
     * @param timeout how long (in seconds) the method should run before timeout
     * @return whatever is returned by the method, if calling it was successful
     */
    public static Object callMethod(String className, Class<?>[] constructorParams,
                              Object[] constructorArgs, String methodName, int timeout) {
        Class<?>[] methodParams = new Class<?>[0];
        Object[] methodArgs = new Object[0];
        return callMethod(className, constructorParams, constructorArgs,
                methodName, methodParams, methodArgs, timeout);
    }

    /**
     * Attempts to call the specified method using the given arguments. This
     * method is used to get what is returned from the method to test against
     * the project solution.
     *
     * NOTE: if the class constructor requires arguments,
     * you must use one of the overloaded callMethod functions.
     *
     * @param className the name of the class
     * @param methodName the name of the method to be called
     * @param methodParams the parameters for the method
     * @param methodArgs the arguments to the method
     * @return whatever is returned by the method, if calling it was successful
     */
    public static Object callMethod(String className, String methodName,
                              Class<?>[] methodParams, Object[] methodArgs) {
        int timeout = 30;
        return callMethod(className, methodName, methodParams,
                methodArgs, timeout);
    }

    /**
     * Attempts to call the specified method using the given arguments. This
     * method is used to get what is returned from the method to test against
     * the project solution.
     *
     * NOTE: if the class constructor requires arguments,
     * you must use one of the overloaded callMethod functions.
     *
     * @param className the name of the class
     * @param methodName the name of the method to be called
     * @param methodParams the parameters for the method
     * @param methodArgs the arguments to the method
     * @param timeout how long (in seconds) the method should run before timeout
     * @return whatever is returned by the method, if calling it was successful
     */
    public static Object callMethod(String className, String methodName,
                              Class<?>[] methodParams, Object[] methodArgs,
                              int timeout) {
        Class<?>[] constructorParams = new Class<?>[0];
        Object[] constructorArgs = new Object[0];
        return callMethod(className, constructorParams, constructorArgs,
                methodName, methodParams, methodArgs, timeout);
    }

    /**
     * Attempts to call the specified method using the given arguments. This
     * method is used to get what is returned from the method to test against
     * the project solution.
     *
     * @param className the name of the class
     * @param constructorParams the parameters for the class constructor
     * @param constructorArgs the arguments for the class constructor
     * @param methodName the name of the method to be called
     * @param methodParams the parameters for the method
     * @param methodArgs the arguments to the method
     * @return whatever is returned by the method, if calling it was successful
     */
    public static Object callMethod(String className, Class<?>[] constructorParams,
                              Object[] constructorArgs,
                              String methodName, Class<?>[] methodParams,
                              Object[] methodArgs) {
        int timeout = 30;
        return callMethod(className, constructorParams, constructorArgs,
                methodName, methodParams, methodArgs, timeout);
    }

    /**
     * Attempts to call the specified method using the given arguments. This
     * method is used to get what is returned from the method to test against
     * the project solution.
     *
     * @param className the name of the class
     * @param constructorParams the parameters for the class constructor
     * @param constructorArgs the arguments for the class constructor
     * @param methodName the name of the method to be called
     * @param methodParams the parameters for the method
     * @param methodArgs the arguments to the method
     * @param timeout how long (in seconds) the method should run before timeout
     * @return whatever is returned by the method, if calling it was successful
     */
    public static Object callMethod(String className, Class<?>[] constructorParams,
                              Object[] constructorArgs,
                              String methodName, Class<?>[] methodParams,
                              Object[] methodArgs,
                              int timeout) {
        Object theClass = getClassInstance(className, constructorParams, constructorArgs);
        Method theMethod = getMethod(className, methodName, methodParams);
        return callMethod(theClass, theMethod, methodArgs, timeout);
    }

    /**
     * Attempts to call the specified method using the given arguments. This
     * method is used to get what is returned from the method to test against
     * the project solution.
     *
     * @param classObject the class object from which the method should be called
     * @param methodName the name of the method to be called
     * @return whatever is returned by the method, if calling it was successful
     */
    public static Object callMethod(Object classObject, String methodName) {
        int timeout = 30;
        return callMethod(classObject, methodName, timeout);
    }

    /**
     * Attempts to call the specified method using the given arguments. This
     * method is used to get what is returned from the method to test against
     * the project solution.
     *
     * @param classObject the class object from which the method should be called
     * @param methodName the name of the method to be called
     * @param timeout how long (in seconds) the method should run before timeout
     * @return whatever is returned by the method, if calling it was successful
     */
    public static Object callMethod(Object classObject, String methodName, int timeout) {
        Class<?>[] methodParams = new Class<?>[0];
        Object[] methodArgs = new Object[0];
        return callMethod(classObject, methodName, methodParams, methodArgs, timeout);
    }

    /**
     * Attempts to call the specified method using the given arguments. This
     * method is used to get what is returned from the method to test against
     * the project solution.
     *
     * @param classObject the class object from which the method should be called
     * @param methodName the name of the method to be called
     * @param methodParams the parameters for the method
     * @param methodArgs the arguments to be passed to theMethod
     * @return whatever is returned by the method, if calling it was successful
     */
    public static Object callMethod(Object classObject, String methodName, Class<?>[] methodParams, Object[] methodArgs) {
        int timeout = 30;
        return callMethod(classObject, methodName, methodParams, methodArgs, timeout);
    }

    /**
     * Attempts to call the specified method using the given arguments. This
     * method is used to get what is returned from the method to test against
     * the project solution.
     *
     * @param classObject the class object from which the method should be called
     * @param methodName the name of the method to be called
     * @param methodParams the parameters for the method
     * @param methodArgs the arguments to be passed to theMethod
     * @param timeout how long (in seconds) the method should run before timeout
     * @return whatever is returned by the method, if calling it was successful
     */
    public static Object callMethod(Object classObject, String methodName, Class<?>[] methodParams, Object[] methodArgs, int timeout) {
        String className = classObject.getClass().getName().substring(packageName.length());
        Method theMethod = getMethod(className, methodName, methodParams);
        return callMethod(classObject, theMethod, methodArgs, timeout);
    }

    /**
     * Attempts to call the given method using the given class and args. This
     * method is used to get what is returned from the method to test against
     * the project solution.
     *
     * @param classObject the class object from which the method should be called
     * @param theMethod the Method object to be invoked using theClass
     * @return whatever is returned by the method, if calling it was successful
     */
    public static Object callMethod(Object classObject, Method theMethod) {
        int timeout = 30;
        return callMethod(classObject, theMethod, timeout);
    }

    /**
     * Attempts to call the given method using the given class and args. This
     * method is used to get what is returned from the method to test against
     * the project solution.
     *
     * @param classObject the class object from which the method should be called
     * @param theMethod the Method object to be invoked using theClass
     * @param timeout how long (in seconds) the method should run before timeout
     * @return whatever is returned by the method, if calling it was successful
     */
    public static Object callMethod(Object classObject, Method theMethod, int timeout) {
        Object[] methodArgs = new Object[0];
        return callMethod(classObject, theMethod, methodArgs, timeout);
    }

    /**
     * Attempts to call the given method using the given class and args. This
     * method is used to get what is returned from the method to test against
     * the project solution.
     *
     * @param classObject the class object from which the method should be called
     * @param theMethod the Method object to be invoked using theClass
     * @param methodArgs the arguments to be passed to theMethod
     * @return whatever is returned by the method, if calling it was successful
     */
    public static Object callMethod(Object classObject, Method theMethod, Object[] methodArgs) {
        int timeout = 30;
        return callMethod(classObject, theMethod, methodArgs, timeout);
    }

    /**
     * Attempts to call the given method using the given class and args. This
     * method is used to get what is returned from the method to test against
     * the project solution.
     *
     * NOTE: This method is the end point of the chain of overloaded methods
     * that call a specific method.
     *
     * @param classObject the class object from which the method should be called
     * @param theMethod the Method object to be invoked using theClass
     * @param methodArgs the arguments to be passed to theMethod
     * @param timeout how long (in seconds) the method should run before timeout
     * @return whatever is returned by the method, if calling it was successful
     */
    public static Object callMethod(Object classObject, Method theMethod,
                              Object[] methodArgs, int timeout) {
        // The assignment inside the assertion must be final, so wrapped
        // answer in Object[].
        final Object[] answer = {null};
        assertTimeoutPreemptively(Duration.ofSeconds(timeout), () -> {
            String msg = "";
            try {
                answer[0] = Objects.requireNonNull(theMethod).invoke(classObject, methodArgs);
            } catch (IllegalAccessException e) {
                System.err.println("callMethod: " + e.getMessage());

                msg = "Autograder error(Other IllegalAccessException). Please report to Professor / TA.";
                fail(msg);
            } catch (InvocationTargetException e) {
                msg = "Error thrown from called method. This may be a problem with your code, or it may be a problem with the test. Please report this to Professor / TA.";
                Throwable rootCause = getRootCause(e);
                if (rootCause instanceof IndexOutOfBoundsException) {
                    throw (IndexOutOfBoundsException) rootCause;
                } else if (rootCause instanceof IllegalArgumentException) {
                    throw (IllegalArgumentException) rootCause;
                }
            }
        }, "__TIMEOUT__");
        return answer[0];

        // The below was used to catch exceptions thrown by the call to invoke()
        // before implementing the assertTimeoutPreemptively call above.
        // Leaving it here for now, until I can be sure it's not needed.
//        String msg;
//        try {
//            return Objects.requireNonNull(theMethod).invoke(theClass, methodArgs);
//        } catch (IllegalAccessException e) {
//            msg = "Autograder error(Oth). Please report to Professor / TA.";
//        } catch (InvocationTargetException e) {
//            msg = "Error thrown from called method. This may be a problem with your code, or it may be a problem with the test. Please report this to Professor / TA.";
//        }
//        fail(msg);
//        return null;
    }
}
