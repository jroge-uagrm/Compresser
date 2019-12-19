package Clases;

/**
 * Ayudantia 1/2017 Eddy Escalante
 */
public class VectorBitGenerico {

    int V[];//vector de bits
    int cbit; //cantidad de bit
    int dim; //dimension
    String VNombre[];
    String VLugar[];
    String VHora[];
    String VFecha[];

    /**public void InsertarDatos(int Nro, int Peso, int Estatura, char Sexo, String Nombre, String Lugar, String Fecha, String Hora) {
        //RecienNacido RN = new RecienNacido(Peso, Estatura, Sexo);
        int mask = RN.n;
        VNombre[Nro] = Nombre; //[Juan, Pedro,-----]
        VLugar[Nro] = Lugar;//[SC,CBBA, LP----]
        VFecha[Nro] = Fecha;//[12/02/1999,10/10/2000,---]
        VHora[Nro] = Hora;///[10:00,11:00,----]
        Insertar(mask, Nro);
    }**/

    public VectorBitGenerico(int cantbit, int Nelementos) {
        int x = ((Nelementos * cantbit) / 32);
        V = new int[x + 1];
        cbit = cantbit;
        dim = Nelementos;

        VNombre = new String[dim];
        VLugar = new String[dim];
        VFecha = new String[dim];
        VHora = new String[dim];
    }

    private int elevado(int b, int exp) {
        int res;
        if (exp == 0) {
            res = 1;
        } else {
            res = 1;
            for (int i = 1; i <= exp; i++) {
                res = res * b;
            }
        }
        return res;
    }

    private int Nbit(int pos) {
        return ((((pos - 1) * cbit)) % 32) + 1;
    }

    private int NEnter(int pos) {
        return ((((pos - 1) * cbit) / 32));
    }

    public void Insertar(int dato, int pos) {
        if (pos <= dim) {
            int x = Nbit(pos);
            int y = NEnter(pos);
            if (x + cbit <= 32) {
                dato = dato << x - 1;
                int mask1 = elevado(2, cbit) - 1;
                mask1 = mask1 << x - 1;
                mask1 = ~mask1;
                V[y] = V[y] & mask1;
                V[y] = V[y] | dato;
            } else {
                int dato1 = dato;
                dato = dato << x - 1;
                int mask1 = elevado(2, cbit) - 1;
                mask1 = mask1 << x - 1;
                mask1 = ~mask1;
                V[y] = V[y] & mask1;
                V[y] = V[y] | dato;
                dato1 = dato1 >>> 32 - x + 1;
                mask1 = elevado(2, cbit) - 1;
                mask1 = mask1 >>> 32 - x + 1;
                mask1 = ~mask1;
                V[y + 1] = V[y + 1] & mask1;
                V[y + 1] = V[y + 1] | dato1;
            }
        } else {
            System.out.print("Error la capacidad fue sobrepasada aumente elementos");
        }
    }

    public int Sacar(int pos) {
        int aux = 0;
        if (pos <= dim) {
            int x = Nbit(pos);
            int y = NEnter(pos);
            if (x + cbit <= 32) {
                aux = V[y];
                aux = aux >>> x - 1;
                int mask1 = elevado(2, cbit) - 1;
                aux = aux & mask1;
            } else {
                int aux2 = V[y];
                aux2 = aux2 >>> x - 1;
                aux = V[y + 1];
                int mask1 = elevado(2, cbit) - 1;
                mask1 = mask1 >>> 32 - x + 1;
                aux = aux & mask1;
                aux = aux << 32 - x + 1;
                aux = aux | aux2;

            }
        }

        return aux;
    }
    

    /*public String Mostrar(int pos) {
        fichamedica R = new fichamedica();
        return "Recien Nacido{" + " \n "
                + "Nro=" + pos + " \n "
                + "Nombre=" + VNombre[pos] + " \n "
                + R.Mostrar(Sacar(pos)) + " \n "
                + "Lugar=" + VLugar[pos] + " \n "
                + "Hora=" + VHora[pos] + " \n "
                + "Fecha=" + VFecha[pos] + " \n "
                + '}';
    }
*/
    void MostrarArreglos() {
        for (int i = 0; i < VNombre.length; i++) {
            System.out.println("******************************" + i);
            System.out.println(
                      "Nombre=" + VNombre[i] + " \n "
                    + "Lugar=" + VLugar[i] + " \n "
                    + "Hora=" + VHora[i] + " \n "
                    + "Fecha=" + VFecha[i] + " \n ");
        }
    }
/*
    public static void main(String[] args) {
        VectorBitGenerico v = new VectorBitGenerico(21, 50);

        // InsertarDatos(int Nro, int Peso, int Estatura, char Sexo, String Nombre, String Lugar)
        v.InsertarDatos(10, 9, 50, 'M', "Pedro", "Santa Cruz", "10/12/1999", "10:00");
        v.InsertarDatos(11, 9, 50, 'M', "Juan", "Santa Cruz", "10/12/1999", "10:00");

        System.out.println(v.Mostrar(10));
        System.out.println(v.Mostrar(11));

        v.MostrarArreglos();
    }
*/
}
