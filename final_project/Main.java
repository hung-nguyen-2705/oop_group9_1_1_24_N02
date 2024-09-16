import java.util.ArrayList;
import java.util.Scanner;

class Sinhvien 
{
    private int ID;
    private String Name;
    private String Nganh;
    private int Diem;
    private int Kyhoc;

    /*Phuong thuc get */
    public int getID()
    {
        return this.ID;
    }

    public String getName()
    {
        return this.Name;
    }

    public String getNganh()
    {
        return this.Nganh;
    }
    public int getDiem()
    {
        return this.Diem;
    }
    public int getKyhoc()
    {
        return this.Kyhoc;
    }

    /*Phuong thuc set */
    public void setId(int d)
    {
        this.ID=d;
    }

    public void setName(String d)
    {
        this.Name=d;
    }

    public void setNganh(String d)
    {
        this.Nganh=d;
    }

    public void setDiem(int d)
    {
        this.Diem=d;
    }

    public void setKyhoc(int d)
    {
        this.Kyhoc=d;
    }

}

class danhsachsinhvien
{
    private ArrayList<Sinhvien> danhsach;
    
    public danhsachsinhvien()
    {
    }
    public danhsachsinhvien(ArrayList<Sinhvien> danhsach)
    {
        this.danhsach=danhsach;
    }

    public void themsinhvien(Sinhvien sv)
    {
        this.danhsach.add(sv);
    }
}

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        danhsachsinhvien dssv = new danhsachsinhvien(); 
        for(int i=0;i<10;i++)
        {   
            System.out.println("Nhap thong tin sinh vien thu" + (i+1));
            if (i>=1){                                    
            String b=sc.nextLine();}
            System.out.println("Nhap ma sinh vien:"); String ID =sc.nextLine();
            System.out.println("Nhap ten:"); String Name =sc.nextLine();                                 
            System.out.println("Nhap Diem:"); int Diem =sc.nextInt();
                                                String a=sc.nextLine();
            System.out.println("Nhap Nganh:"); String Nganh =sc.nextLine();
            System.out.println("Nhap Ky hoc:"); int Kyhoc =sc.nextInt();

        }
    }
}
