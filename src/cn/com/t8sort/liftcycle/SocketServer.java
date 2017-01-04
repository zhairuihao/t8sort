package cn.com.t8sort.liftcycle;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/** 
 * @author  作者 E-mail: 
 * @date 创建时间：2017年1月4日 上午9:29:59 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class SocketServer extends DefaultLifecycle {
	
	private ServerSocket acceptor = null;
    private int port = 9527;
    /* 
     * @see DefaultLifecycle#init0()
     */
    @Override
    protected void init0() throws LifecycleException {
        try {
            acceptor = new ServerSocket(port);
        } catch (IOException e) {
            throw new LifecycleException(e);
        }
    }

    /* 
     * @see DefaultLifecycle#start0()
     */
    @Override
    protected void start0() throws LifecycleException {
        Socket socket = null;
        try {
            socket = acceptor.accept();
            //do something with socket

        } catch (IOException e) {
            throw new LifecycleException(e);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    /* 
     * @see DefaultLifecycle#destroy0()
     */
    @Override
    protected void destroy0() throws LifecycleException {
        if (acceptor != null) {
            try {
                acceptor.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
