package com.grupo2tdpnicomarcosema;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {
        AtomicInteger x = new AtomicInteger(2);
	    new Thread(() ->
        {
	      for(int i=0 ; i<20 ; i++)
          {
              x.getAndIncrement();
              System.out.println(i+" <>");
              try{Thread.sleep(500);}catch (Exception e){};
          }
        }).start();
        new Thread(() ->
        {
	      for(int i=0 ; i<20 ; i++)
          {
              System.out.println(" ---- " + i);
              try{Thread.sleep(500);}catch (Exception e){};
          }
        }).start();

    }
}
