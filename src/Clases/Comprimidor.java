/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
import java.lang.System;

/**
 *
 * @author Jroge
 */
public class Comprimidor {
    public VectorBitGenerico co;
    int cant;
    String [] nombres;
    public Comprimidor(){
        nombres=new String[1000];
        
    }
    public String Comprimir(String s){
        int can = s.length();
        co=new VectorBitGenerico(6,can);
        for(int i = 1; i<=can;i++){
            int x = s.codePointAt(i-1);
            if(x>47 && x<58){
                x=x-48;
                co.Insertar(x, i);
            }
            else if(x>64 && x<91){
                x=x-55;
                co.Insertar(x, i);
            }
            else if(x>96 && x<123){
                x=x-61;
                co.Insertar(x, i);
            }
            else if(x==32){
                x=62;
                co.Insertar(x, i);
            }else if(x==10){
                x=63;
                co.Insertar(x, i);
            }
        }
        String k ="";
        for(int j = 1; j<=can;j++){
            int y = co.Sacar(j);System.out.println(y);
            for(int h = 1; h<=6;h++){
                int z = y&1;
                k=Integer.toString(z)+k;
                y = y>>1;
            }
    }
//----------------------------------------------------------        
     if(k.length()%8!=0){
         for(int i = 1; i<=k.length()%8;i++){
             k="0"+k;
         }
     }int f = k.length()-1; String L = "";
     for(int j=1;j<=k.length()/8;j++){
         int p = 0; int co=0;
         for(int m=1;m<=8;m++){
             char ch = k.charAt(f);
             String chh = Character.toString(ch);
             int chhh = Integer.parseInt(chh);
             for(int t = 1; t<=co;t++){
                 chhh=chhh<<1;
             }
             p = p | chhh; co++;f--;
         }
         if(p!=0){
             char cha = (char)p;
             L = Character.toString(cha)+L;
         }
         
     }
        return L;
    }   
     public String Descomprimir(String s){
       int can = s.length(); int j = can-1;
       VectorBitGenerico c = new VectorBitGenerico(6,can);
       String k = "";
       for(int i = 1; i<= can;i++){
           int y = s.codePointAt(j);
           for(int w = 1;w<=8;w++){
               int z = y&1;
               k = Integer.toString(z)+k;
               y = y >> 1 ;
           }j--;
       }if(k.length()%6!=0){
           for(int i=1;i<=k.length()%6;i++){
               k="0"+k;
           }
       }
           int f = k.length()-1;String nu = "";
           for(int a = 1; a<=k.length()/6;a++){
               int p = 0; int co = 0;
               for(int m = 1; m<=6; m++){
                   char ch = k.charAt(f);
                   String chh = Character.toString(ch);
                   int chhh = Integer.parseInt(chh);
                    for(int t = 1; t<=co;t++){
                        chhh=chhh<<1;
                    }
                p = p | chhh; co++;f--;
               }
               if(p>=0 && p<=9){
                   p = p + 48;
               }else if(p>=10 && p<=35){
                   p = p + 55;
               }else if(p>=36 && p<=61){
                   p = p + 61;
               }else if(p==62){
                   p = 32;
               }else if(p==63){
                   p = 10;
               }
               char cha = (char)p;
               System.out.println(cha);
               nu = nu+Character.toString(cha);
           }return nu;
}
    
    
    
    
    
//    public String Comprimir(String s){
//        int n=(s.length()+3)/4;
//        co=new VectorBitGenerico(32,n);cant=n;
//        int j=0;
//        for(int i=1;i<=n;i++){
//            int p=0;int c=1;
//            while(j<s.length() && c<=4){
//                p=p<<8;
//                p=p|(s.charAt(j)-32);
//                j++;c++;
//            }
//            co.Insertar(p,i);
//        }s="";
//        for(int i=1;i<=cant;i++){
//            int x=co.Sacar(i);
//            s=s+Integer.toBinaryString(x);
//        }
//        return s;
//    }
    
}