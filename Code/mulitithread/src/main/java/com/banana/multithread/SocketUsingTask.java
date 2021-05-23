package com.banana.multithread;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

public abstract class SocketUsingTask<T> implements CancellableTask<T> {
    private Socket socket;

    private volatile String a;

    @Override
    public void cancel() {
        try {
            if (socket != null) {
                socket.close();

                Class.forName(String.valueOf(SocketUsingTask.class));


            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RunnableFuture<T> newTask() {
        return new FutureTask<T>(this) {

            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                try {
                    SocketUsingTask.this.cancel();
                } finally {
                    return super.cancel(mayInterruptIfRunning);
                }
            }
        };
    }

    @Override
    public T call() throws Exception {
        return null;
    }
}
