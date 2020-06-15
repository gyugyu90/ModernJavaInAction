package com.example.modernjava.part3.responsibilitychain;

public abstract class ProcessingObject<T> {

    protected ProcessingObject<T> successor; // 계승자들의 Chain

    public void setSuccessor(ProcessingObject<T> successor) {
        this.successor = successor;
    }

    public T handle(T input) {
        T r = handleWork(input);
        if (successor != null) {
            return successor.handle(r);
        }
        return r;
    }

    abstract protected T handleWork(T input);

}
