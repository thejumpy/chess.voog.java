import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import javax.swing.JFrame;
import javax.swing.JPanel;
public class Male {
    public  static ArrayList<Malend> nupud = new ArrayList<>(); // loome malendite jaoks listi
    public static Malend liigutatavMalend=null;
    public static int sihtPunkti_x;
    public static int sihtPunkti_y;
    public static int kord= 0;
    public static ArrayList<String> käigud =new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedImage all= ImageIO.read(new File("favpng_chess-piece-knight-rook-clip-art.png")); //laeme pildi kõigist malenditest
        Image[] pilt =new Image[12]; //Lõikame pildi 12 osaks niimoodi et saame 12 erinevat pilti, kus iga pildi peal on üks malend
        int järjekorranumber=0;
        for (int y=0; y<800; y+=400){
            for(int x=0; x<1800; x+=300){
                pilt[järjekorranumber]=all.getSubimage(x,y,300,400).getScaledInstance(90,90,BufferedImage.SCALE_AREA_AVERAGING );
                järjekorranumber++;
            }
        }

        Malend valge_vanker1=new Malend(0,7,"vanker",true,nupud); //Loome malelaua algseisu
        Malend valge_ratsu1=new Malend(1,7,"ratsu",true,nupud);
        Malend valge_oda1=new Malend(2,7,"oda",true,nupud);
        Malend valge_lipp=new Malend(3,7,"lipp",true,nupud);
        Malend valge_kuningas=new Malend(4,7,"kuningas",true,nupud);
        Malend valge_oda2=new Malend(5,7,"oda",true,nupud);
        Malend valge_ratsu2=new Malend(6,7,"ratsu",true,nupud);
        Malend valge_vanker2=new Malend(7,7,"vanker",true,nupud);
        Malend valge_ettur1=new Malend(0,6,"ettur",true,nupud);
        Malend valge_ettur2=new Malend(1,6,"ettur",true,nupud);
        Malend valge_ettur3=new Malend(2,6,"ettur",true,nupud);
        Malend valge_ettur4=new Malend(3,6,"ettur",true,nupud);
        Malend valge_ettur5=new Malend(4,6,"ettur",true,nupud);
        Malend valge_ettur6=new Malend(5,6,"ettur",true,nupud);
        Malend valge_ettur7=new Malend(6,6,"ettur",true,nupud);
        Malend valge_ettur8=new Malend(7,6,"ettur",true,nupud);
        Malend must_vanker1=new Malend(0,0,"vanker",false,nupud);
        Malend must_ratsu1=new Malend(1,0,"ratsu",false,nupud);
        Malend must_oda1=new Malend(2,0,"oda",false,nupud);
        Malend must_lipp=new Malend(3,0,"lipp",false,nupud);
        Malend must_kuningas=new Malend(4,0,"kuningas",false,nupud);
        Malend must_oda2=new Malend(5,0,"oda",false,nupud);
        Malend must_ratsu2=new Malend(6,0,"ratsu",false,nupud);
        Malend must_vanker2=new Malend(7,0,"vanker",false,nupud);
        Malend must_ettur1=new Malend(0,1,"ettur",false,nupud);
        Malend must_ettur2=new Malend(1,1,"ettur",false,nupud);
        Malend must_ettur3=new Malend(2,1,"ettur",false,nupud);
        Malend must_ettur4=new Malend(3,1,"ettur",false,nupud);
        Malend must_ettur5=new Malend(4,1,"ettur",false,nupud);
        Malend must_ettur6=new Malend(5,1,"ettur",false,nupud);
        Malend must_ettur7=new Malend(6,1,"ettur",false,nupud);
        Malend must_ettur8=new Malend(7,1,"ettur",false,nupud);
        JFrame raam = new JFrame();
        raam.setBounds(400, 20, 744, 760); //Loome akna kuhu sisse teeme hiljem malelaua

        JPanel malelaud=new JPanel(){ //loome maleruudustiku
            @Override
            public void paint(Graphics g) {
                boolean Valge=true;
                for(int y= 0;y<8;y++){
                    for(int x= 0;x<8;x++){
                        if(Valge){
                            g.setColor(Color.lightGray); //Määrame ruudustiku värvid
                        }else{
                            g.setColor(Color.DARK_GRAY);
                        }
                        g.fillRect(x*90, y*90, 90, 90);
                        Valge=!Valge;
                    }
                    Valge=!Valge;
                }
                for (Malend element: nupud){ //Anname eelnevalt lõigatud piltidele sõnedena väärtused
                    int järjekorranumber=0;
                    if(element.getMalendiTüüp().equals("vanker")){
                        järjekorranumber=0;
                    }
                    if(element.getMalendiTüüp().equals("oda")){
                        järjekorranumber=1;
                    } if(element.getMalendiTüüp().equals("lipp")){
                        järjekorranumber=2;
                    } if(element.getMalendiTüüp().equals("kuningas")){
                        järjekorranumber=3;
                    } if(element.getMalendiTüüp().equals("ratsu")){
                        järjekorranumber=4;
                    }
                    if(element.getMalendiTüüp().equals("ettur")){
                        järjekorranumber=5;
                    }
                    if(element.getvalgeNupp()){ //eelnevad olid mustade nuppude jaoks, nüüd liidame 6 et saada valged nupud
                        järjekorranumber+=6;
                    }
                    g.drawImage(pilt[järjekorranumber],element.getX(),element.getY(),this );

                }
            }
        };



        raam.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(liigutatavMalend != null){
                    liigutatavMalend.x=e.getX()-45;
                    liigutatavMalend.y=e.getY()-45;
                    raam.repaint();
                }


            }


            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        raam.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {


            }

            @Override
            public void mousePressed(MouseEvent e) {


                liigutatavMalend=getMalend(e.getX(),e.getY());

                raam.repaint();
            }

            @Override
             public void mouseReleased(MouseEvent e) {
                sihtPunkti_x = e.getX()/90;
                sihtPunkti_y =e.getY()/90;

                try {
                    liigutatavMalend.legaalne(e.getX() / 90, e.getY() / 90);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                {
                }
                    raam.repaint();



            }


            @Override
            public void mouseEntered(MouseEvent e) {

            }



            @Override
            public void mouseExited(MouseEvent e) {

            }

        });

        raam.add(malelaud); //lisame maleruudustiku aknasse
        raam.setVisible(true);

    }

    public  static Malend getMalend(int x, int y){ //getmeetod malendite leidmiseks listist
        int x_telg=x/90;
        int y_telg=y/90;
        for (Malend n: nupud) {
            if (n.getX_telg()== x_telg) {
                if (n.getY_telg() == y_telg) {
                    return n;
                }
            }
        }
        return  null; // kui ruudul ei ole ühtegi nuppu tagastab nulli


    }



}
