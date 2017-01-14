import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Tato komplexní třída MoveLocation nám nastaví lokaci pro dané pole a pro daný tile. Mechanismus funguje jako u MoveCard.
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public class MoveLocation extends Action
{
    
/**
 * V atributu location máme 5 druhy lokací, kde každá karta má 9 polí (lokací):
 *      1 - louka
 *      2 - cesta
 *      3 - město
 *      4 - klášter
 *      5 - konec cesty
 */  
    private int[][][] location = new int[91][72][9];
    private int[][] locationAbsolute = new int[91][9];
    private Location[][] graphicLocation = new Location[91][9];
    
     /**
     * Metoda isEnabled je povolení, neboli vrací true.
     */    
    public boolean isEnabled() {
        return true;
    }
    
    public void turn(int x,int y){
              int a = location[x][y][1];
              int b = location[x][y][5];
              int c = location[x][y][7];
              location[x][y][1] = location[x][y][3]; 
              location[x][y][5] = a; 
              location[x][y][7] = b; 
              location[x][y][3] = c;   
              
              int d = location[x][y][0];
              int e = location[x][y][2];
              int f = location[x][y][8];
              location[x][y][0] = location[x][y][6]; 
              location[x][y][2] = d; 
              location[x][y][8] = e; 
              location[x][y][6] = f; 
    }
    
    public int getLocation(int x, int y, int z){
            return location[x][y][z];
    }
    
    public int getLocationAbsolute(int x, int y){
            return locationAbsolute[x][y];
    }
    
    public void moveLeftLocation(int y, int x){
        location[y][x][1] = location[y+1][x][1];
        location[y][x][5] = location[y+1][x][5];
        location[y][x][7] = location[y+1][x][7];
        location[y][x][3] = location[y+1][x][3];
        
        location[y][x][0] = location[y+1][x][0];
        location[y][x][2] = location[y+1][x][2];
        location[y][x][8] = location[y+1][x][8];
        location[y][x][6] = location[y+1][x][6];
        
        location[y][x][4] = location[y+1][x][4];
        
        location[y+1][x][1] = 0;
        location[y+1][x][5] = 0;
        location[y+1][x][7] = 0;
        location[y+1][x][3] = 0;
        
        location[y+1][x][0] = 0;
        location[y+1][x][2] = 0;
        location[y+1][x][8] = 0;
        location[y+1][x][6] = 0;
        
        location[y+1][x][4] = 0;
    }  
    
    public void moveRightLocation(int y, int x){
        location[y][x][1] = location[y-1][x][1];
        location[y][x][5] = location[y-1][x][5];
        location[y][x][7] = location[y-1][x][7];
        location[y][x][3] = location[y-1][x][3];
        
        location[y][x][0] = location[y-1][x][0];
        location[y][x][2] = location[y-1][x][2];
        location[y][x][8] = location[y-1][x][8];
        location[y][x][6] = location[y-1][x][6];
        
        location[y][x][4] = location[y-1][x][4];
        
        location[y-1][x][1] = 0;
        location[y-1][x][5] = 0;
        location[y-1][x][7] = 0;
        location[y-1][x][3] = 0;
        
        location[y-1][x][0] = 0;
        location[y-1][x][2] = 0;
        location[y-1][x][8] = 0;
        location[y-1][x][6] = 0;
        
        location[y-1][x][4] = 0;
    }
     
    public void moveUpLocation(int y, int x){
        location[y][x][1] = location[y+13][x][1];
        location[y][x][5] = location[y+13][x][5];
        location[y][x][7] = location[y+13][x][7];
        location[y][x][3] = location[y+13][x][3];
        
        location[y][x][0] = location[y+13][x][0];
        location[y][x][2] = location[y+13][x][2];
        location[y][x][8] = location[y+13][x][8];
        location[y][x][6] = location[y+13][x][6];
        
        location[y][x][4] = location[y+13][x][4];
        
        location[y+13][x][1] = 0;
        location[y+13][x][5] = 0;
        location[y+13][x][7] = 0;
        location[y+13][x][3] = 0;
        
        location[y+13][x][0] = 0;
        location[y+13][x][2] = 0;
        location[y+13][x][8] = 0;
        location[y+13][x][6] = 0;
        
        location[y+13][x][4] = 0;
    }
     
    public void moveDownLocation(int y, int x){
        location[y][x][1] = location[y-13][x][1];
        location[y][x][5] = location[y-13][x][5];
        location[y][x][7] = location[y-13][x][7];
        location[y][x][3] = location[y-13][x][3];
        
        location[y][x][0] = location[y-13][x][0];
        location[y][x][2] = location[y-13][x][2];
        location[y][x][8] = location[y-13][x][8];
        location[y][x][6] = location[y-13][x][6];
        
        location[y][x][4] = location[y-13][x][4];
        
        location[y-13][x][1] = 0;
        location[y-13][x][5] = 0;
        location[y-13][x][7] = 0;
        location[y-13][x][3] = 0;
        
        location[y-13][x][0] = 0;
        location[y-13][x][2] = 0;
        location[y-13][x][8] = 0;
        location[y-13][x][6] = 0;
        
        location[y-13][x][4] = 0;
    }
    
    public void setLocation (int y, int x){
            if(x==0){
              location[y][x][1] = 3; 
              location[y][x][5] = 2; 
              location[y][x][7] = 1; 
              location[y][x][3] = 2; 
              
              location[y][x][0] = 1; 
              location[y][x][2] = 1; 
              location[y][x][8] = 1; 
              location[y][x][6] = 1; 
              
              location[y][x][4] = 2; 
            }
            if((x>=1)&&(x<=4)){
              location[y][x][1] = 1; 
              location[y][x][5] = 1; 
              location[y][x][7] = 1; 
              location[y][x][3] = 1;  
              
              location[y][x][0] = 1; 
              location[y][x][2] = 1; 
              location[y][x][8] = 1; 
              location[y][x][6] = 1; 
              
              location[y][x][4] = 4; 
            }     
            if((x>=5)&&(x<=6)){
              location[y][x][1] = 1; 
              location[y][x][5] = 1; 
              location[y][x][7] = 2; 
              location[y][x][3] = 1;  
              
              location[y][x][0] = 1; 
              location[y][x][2] = 1; 
              location[y][x][8] = 1; 
              location[y][x][6] = 1; 
              
              location[y][x][4] = 4; 
            }
            if((x>=7)&&(x<=7)){
              location[y][x][1] = 3; 
              location[y][x][5] = 3; 
              location[y][x][7] = 3; 
              location[y][x][3] = 3;  
              
              location[y][x][0] = 3; 
              location[y][x][2] = 3; 
              location[y][x][8] = 3; 
              location[y][x][6] = 3; 
              
              location[y][x][4] = 3; 
            }     
            if((x>=8)&&(x<=10)){
              location[y][x][1] = 3; 
              location[y][x][5] = 3; 
              location[y][x][7] = 1; 
              location[y][x][3] = 3;  
              
              location[y][x][0] = 3; 
              location[y][x][2] = 2; 
              location[y][x][8] = 3; 
              location[y][x][6] = 3; 
              
              location[y][x][4] = 3; 
            }
            if((x>=11)&&(x<=11)){
              location[y][x][1] = 3; 
              location[y][x][5] = 3; 
              location[y][x][7] = 1; 
              location[y][x][3] = 3;  
              
              location[y][x][0] = 3; 
              location[y][x][2] = 3; 
              location[y][x][8] = 3; 
              location[y][x][6] = 3;  
              
              location[y][x][4] = 3; 
            }     
            if((x>=12)&&(x<=12)){
              location[y][x][1] = 3; 
              location[y][x][5] = 3; 
              location[y][x][7] = 2; 
              location[y][x][3] = 3;  
              
              location[y][x][0] = 3; 
              location[y][x][2] = 3; 
              location[y][x][8] = 1; 
              location[y][x][6] = 1; 
              
              location[y][x][4] = 3; 
            }
            if((x>=13)&&(x<=14)){
              location[y][x][1] = 3; 
              location[y][x][5] = 3; 
              location[y][x][7] = 2; 
              location[y][x][3] = 3;  
              
              location[y][x][0] = 3; 
              location[y][x][2] = 3; 
              location[y][x][8] = 1; 
              location[y][x][6] = 1; 
              
              location[y][x][4] = 3; 
            }     
            if((x>=15)&&(x<=17)){
              location[y][x][1] = 3; 
              location[y][x][5] = 1; 
              location[y][x][7] = 1; 
              location[y][x][3] = 3;  
              
              location[y][x][0] = 3; 
              location[y][x][2] = 1; 
              location[y][x][8] = 1; 
              location[y][x][6] = 1;  
              
              location[y][x][4] = 1; 
            }
            if((x>=18)&&(x<=19)){
              location[y][x][1] = 3; 
              location[y][x][5] = 1; 
              location[y][x][7] = 1; 
              location[y][x][3] = 3;  
              
              location[y][x][0] = 3; 
              location[y][x][2] = 1; 
              location[y][x][8] = 1; 
              location[y][x][6] = 1; 
              
              location[y][x][4] = 1; 
            }     
            if((x>=20)&&(x<=22)){
              location[y][x][1] = 3; 
              location[y][x][5] = 2; 
              location[y][x][7] = 2; 
              location[y][x][3] = 3;  
              
              location[y][x][0] = 3; 
              location[y][x][2] = 1; 
              location[y][x][8] = 1; 
              location[y][x][6] = 1; 
              
              location[y][x][4] = 1; 
            }
            if((x>=23)&&(x<=24)){
              location[y][x][1] = 3; 
              location[y][x][5] = 2; 
              location[y][x][7] = 2; 
              location[y][x][3] = 3;  
              
              location[y][x][0] = 3; 
              location[y][x][2] = 1; 
              location[y][x][8] = 1; 
              location[y][x][6] = 1; 
              
              location[y][x][4] = 1; 
            }     
            if((x>=25)&&(x<=25)){
              location[y][x][1] = 1; 
              location[y][x][5] = 3; 
              location[y][x][7] = 1; 
              location[y][x][3] = 3;  
              
              location[y][x][0] = 3; 
              location[y][x][2] = 3; 
              location[y][x][8] = 3; 
              location[y][x][6] = 3; 
              
              location[y][x][4] = 3; 
            }
            if((x>=26)&&(x<=27)){
              location[y][x][1] = 1; 
              location[y][x][5] = 3; 
              location[y][x][7] = 1; 
              location[y][x][3] = 3;  
              
              location[y][x][0] = 3; 
              location[y][x][2] = 3; 
              location[y][x][8] = 3; 
              location[y][x][6] = 3; 
              
              location[y][x][4] = 3; 
            }     
            if((x>=28)&&(x<=29)){
              location[y][x][1] = 3; 
              location[y][x][5] = 1; 
              location[y][x][7] = 1; 
              location[y][x][3] = 3;  
              
              location[y][x][0] = 1; 
              location[y][x][2] = 1; 
              location[y][x][8] = 1; 
              location[y][x][6] = 2; 
              
              location[y][x][4] = 1; 
            }
            if((x>=30)&&(x<=32)){
              location[y][x][1] = 3; 
              location[y][x][5] = 1; 
              location[y][x][7] = 3; 
              location[y][x][3] = 1;  
              
              location[y][x][0] = 1; 
              location[y][x][2] = 1; 
              location[y][x][8] = 1; 
              location[y][x][6] = 1; 
              
              location[y][x][4] = 1; 
            }     
            if((x>=33)&&(x<=37)){
              location[y][x][1] = 3; 
              location[y][x][5] = 1; 
              location[y][x][7] = 1; 
              location[y][x][3] = 1;  
              
              location[y][x][0] = 1; 
              location[y][x][2] = 1; 
              location[y][x][8] = 1; 
              location[y][x][6] = 1;  
              
              location[y][x][4] = 1; 
            }
            if((x>=38)&&(x<=40)){
              location[y][x][1] = 3; 
              location[y][x][5] = 1; 
              location[y][x][7] = 2; 
              location[y][x][3] = 2;  
              
              location[y][x][0] = 1; 
              location[y][x][2] = 1; 
              location[y][x][8] = 1; 
              location[y][x][6] = 1; 
              
              location[y][x][4] = 1; 
            }     
            if((x>=41)&&(x<=43)){
              location[y][x][1] = 3; 
              location[y][x][5] = 2; 
              location[y][x][7] = 2; 
              location[y][x][3] = 1;  
              
              location[y][x][0] = 1; 
              location[y][x][2] = 1; 
              location[y][x][8] = 1; 
              location[y][x][6] = 1; 
              
              location[y][x][4] = 1; 
            }
            if((x>=44)&&(x<=46)){
              location[y][x][1] = 3; 
              location[y][x][5] = 2; 
              location[y][x][7] = 2; 
              location[y][x][3] = 2;  
              
              location[y][x][0] = 1; 
              location[y][x][2] = 1; 
              location[y][x][8] = 1; 
              location[y][x][6] = 1; 
              
              location[y][x][4] = 5; 
            }     
            if((x>=47)&&(x<=49)){
              location[y][x][1] = 3; 
              location[y][x][5] = 2; 
              location[y][x][7] = 1; 
              location[y][x][3] = 2;  
              
              location[y][x][0] = 1; 
              location[y][x][2] = 1; 
              location[y][x][8] = 1; 
              location[y][x][6] = 1; 
              
              location[y][x][4] = 2; 
            }
            if((x>=50)&&(x<=57)){
              location[y][x][1] = 2; 
              location[y][x][5] = 1; 
              location[y][x][7] = 2; 
              location[y][x][3] = 1;  
              
              location[y][x][0] = 1; 
              location[y][x][2] = 1; 
              location[y][x][8] = 1; 
              location[y][x][6] = 1; 
              
              location[y][x][4] = 2; 
            }     
            if((x>=58)&&(x<=66)){
              location[y][x][1] = 1; 
              location[y][x][5] = 1; 
              location[y][x][7] = 2; 
              location[y][x][3] = 2;  
              
              location[y][x][0] = 1; 
              location[y][x][2] = 1; 
              location[y][x][8] = 1; 
              location[y][x][6] = 1;  
              
              location[y][x][4] = 2; 
            }
            if((x>=67)&&(x<=70)){
              location[y][x][1] = 1; 
              location[y][x][5] = 2; 
              location[y][x][7] = 2; 
              location[y][x][3] = 2;  
              
              location[y][x][0] = 1; 
              location[y][x][2] = 1; 
              location[y][x][8] = 1; 
              location[y][x][6] = 1;  
              
              location[y][x][4] = 5; 
            }     
            if((x>=71)&&(x<=71)){
              location[y][x][1] = 2; 
              location[y][x][5] = 2; 
              location[y][x][7] = 2; 
              location[y][x][3] = 2;  
              
              location[y][x][0] = 1; 
              location[y][x][2] = 1; 
              location[y][x][8] = 1; 
              location[y][x][6] = 1; 
              
              location[y][x][4] = 5; 
            }
        }
    /**
     * Trvalé nastavení lokace pro danou kartu na definovany tile.
     */    
    public void setLocationAbsolute (int x, int y, Tile tile){
              locationAbsolute[x][1] = location[x][y][1]; 
              locationAbsolute[x][5] = location[x][y][5]; 
              locationAbsolute[x][7] = location[x][y][7]; 
              locationAbsolute[x][3] = location[x][y][3]; 
              
              locationAbsolute[x][0] = location[x][y][0]; 
              locationAbsolute[x][2] = location[x][y][2]; 
              locationAbsolute[x][8] = location[x][y][8]; 
              locationAbsolute[x][6] = location[x][y][6]; 
              
              locationAbsolute[x][4] = location[x][y][4]; 
              
              graphicLocation[x][1] = new Location(locationAbsolute[x][1], tile.getX(), (tile.getY()-30),1);
              getWorld().addObject(graphicLocation[x][1], tile.getX(), (tile.getY()-30));  
              
              graphicLocation[x][5] = new Location(locationAbsolute[x][5], (tile.getX()+30), tile.getY(),5);
              getWorld().addObject(graphicLocation[x][5], (tile.getX()+30), tile.getY());
                            
              graphicLocation[x][7] = new Location(locationAbsolute[x][7], tile.getX(), (tile.getY()+30),7);
              getWorld().addObject(graphicLocation[x][7], tile.getX(), (tile.getY()+30));  
              
              graphicLocation[x][3] = new Location(locationAbsolute[x][3], (tile.getX()-30), tile.getY(),3);
              getWorld().addObject(graphicLocation[x][3], (tile.getX()-30), tile.getY());
                            
              graphicLocation[x][0] = new Location(locationAbsolute[x][0], (tile.getX()-30), (tile.getY()-30),0);
              getWorld().addObject(graphicLocation[x][0], (tile.getX()-30), (tile.getY()-30));  
              
              graphicLocation[x][2] = new Location(locationAbsolute[x][2],(tile.getX()+30), (tile.getY()-30),2);
              getWorld().addObject(graphicLocation[x][2],(tile.getX()+30), (tile.getY()-30));
                            
              graphicLocation[x][8] = new Location(locationAbsolute[x][8], (tile.getX()+30), (tile.getY()+30),8);
              getWorld().addObject(graphicLocation[x][8], (tile.getX()+30), (tile.getY()+30));  
              
              graphicLocation[x][6] = new Location(locationAbsolute[x][6], (tile.getX()-30), (tile.getY()+30),6);
              getWorld().addObject(graphicLocation[x][6], (tile.getX()-30), (tile.getY()+30));
                            
              graphicLocation[x][4] = new Location(locationAbsolute[x][4], tile.getX(), tile.getY(),4);
              getWorld().addObject(graphicLocation[x][4], tile.getX(), tile.getY());  
            }   
    
    public Location getLocation(int x, int y){
        return graphicLocation[x][y];
    }     
            
    /**
     * Mechanismus pro vyber lokace, zdali muzeme kartu umistit nebo ne. Jelikoz mame 3 ruzne druhy stran karty a navazovat na sebe může jen stejná strana
     * musela být metodika precizně ošetřena, navíc platí jiná pravidla pro umisťování karty např na okraji hrací desky a u prostřed.
     */
    public boolean tryLocation(int y,Card[] card,Deck deck){
      if(((y>=14)&&(y<=24))||((y>=27)&&(y<=37))||((y>=40)&&(y<=50))||((y>=53)&&(y<=63))||((y>=66)&&(y<=76))){
         if(((locationAbsolute[y-13][7])==0)&&((locationAbsolute[y+13][1])==0)&&((locationAbsolute[y+1][3])==0)&&((locationAbsolute[y-1][5])==0)){
          return false;
          }else{
            if((location[y][card[deck.getNumber()].getRealNumber()][1]==locationAbsolute[y-13][7])||(0==locationAbsolute[y-13][7])){
                if((location[y][card[deck.getNumber()].getRealNumber()][7]==locationAbsolute[y+13][1])||(0==locationAbsolute[y+13][1])){
                    if((location[y][card[deck.getNumber()].getRealNumber()][3]==locationAbsolute[y-1][5])||(0==locationAbsolute[y-1][5])){
                        if((location[y][card[deck.getNumber()].getRealNumber()][5]==locationAbsolute[y+1][3])||(0==locationAbsolute[y+1][3])){
                            return true;
                        }else{return false;}
                    }else{return false;}
                }else{return false;}
            }else{return false;}
          }
        }else if((y>=1)&&(y<=11)){
         if(((locationAbsolute[y+13][1])==0)&&((locationAbsolute[y+1][3])==0)&&((locationAbsolute[y-1][5])==0)){
          return false;
          }else{
            if((location[y][card[deck.getNumber()].getRealNumber()][7]==locationAbsolute[y+13][1])||(0==locationAbsolute[y+13][1])){
                    if((location[y][card[deck.getNumber()].getRealNumber()][3]==locationAbsolute[y-1][5])||(0==locationAbsolute[y-1][5])){
                        if((location[y][card[deck.getNumber()].getRealNumber()][5]==locationAbsolute[y+1][3])||(0==locationAbsolute[y+1][3])){
                            return true;
                        }else{return false;}
                    }else{return false;}
                }else{return false;}
          }
        }else if((y>=79)&&(y<=89)){
         if(((locationAbsolute[y-13][7])==0)&&((locationAbsolute[y+1][3])==0)&&((locationAbsolute[y-1][5])==0)){
          return false;
          }else{
            if((location[y][card[deck.getNumber()].getRealNumber()][1]==locationAbsolute[y-13][7])||(0==locationAbsolute[y-13][7])){
                if((location[y][card[deck.getNumber()].getRealNumber()][3]==locationAbsolute[y-1][5])||(0==locationAbsolute[y-1][5])){
                        if((location[y][card[deck.getNumber()].getRealNumber()][5]==locationAbsolute[y+1][3])||(0==locationAbsolute[y+1][3])){
                            return true;
                        }else{return false;}
                    }else{return false;}
                }else{return false;}
          }
        }else if((y==13)||(y==26)||(y==39)||(y==52)||(y==65)){
        if(((locationAbsolute[y-13][7])==0)&&((locationAbsolute[y+13][1])==0)&&((locationAbsolute[y+1][3])==0)){
          return false;
          }else{
            if((location[y][card[deck.getNumber()].getRealNumber()][1]==locationAbsolute[y-13][7])||(0==locationAbsolute[y-13][7])){
                if((location[y][card[deck.getNumber()].getRealNumber()][7]==locationAbsolute[y+13][1])||(0==locationAbsolute[y+13][1])){
                        if((location[y][card[deck.getNumber()].getRealNumber()][5]==locationAbsolute[y+1][3])||(0==locationAbsolute[y+1][3])){
                            return true;
                        }else{return false;}
                }else{return false;}
            }else{return false;}
          }
        }else if((y==25)||(y==38)||(y==51)||(y==64)||(y==77)){
        if(((locationAbsolute[y-13][7])==0)&&((locationAbsolute[y+13][1])==0)&&((locationAbsolute[y-1][3])==0)){
          return false;
          }else{
            if((location[y][card[deck.getNumber()].getRealNumber()][1]==locationAbsolute[y-13][7])||(0==locationAbsolute[y-13][7])){
                if((location[y][card[deck.getNumber()].getRealNumber()][7]==locationAbsolute[y+13][1])||(0==locationAbsolute[y+13][1])){
                        if((location[y][card[deck.getNumber()].getRealNumber()][3]==locationAbsolute[y-1][5])||(0==locationAbsolute[y-1][5])){
                            return true;
                        }else{return false;}
                }else{return false;}
            }else{return false;}
          }
        }else if(y==0){
         if(((locationAbsolute[y+13][1])==0)&&((locationAbsolute[y+1][3])==0)){
          return false;
          }else{
                if((location[y][card[deck.getNumber()].getRealNumber()][7]==locationAbsolute[y+13][1])||(0==locationAbsolute[y+13][1])){
                        if((location[y][card[deck.getNumber()].getRealNumber()][5]==locationAbsolute[y+1][3])||(0==locationAbsolute[y+1][3])){
                            return true;
                        }else{return false;}
                }else{return false;}
          }   
        }else if(y==12){
         if(((locationAbsolute[y+13][1])==0)&&((locationAbsolute[y-1][5])==0)){
          return false;
          }else{
                if((location[y][card[deck.getNumber()].getRealNumber()][7]==locationAbsolute[y+13][1])||(0==locationAbsolute[y+13][1])){
                    if((location[y][card[deck.getNumber()].getRealNumber()][3]==locationAbsolute[y-1][5])||(0==locationAbsolute[y-1][5])){
                            return true;
                    }else{return false;}
                }else{return false;}
          }   
        }else if(y==78){
         if(((locationAbsolute[y-13][7])==0)&&((locationAbsolute[y+1][3])==0)){
          return false;
          }else{
            if((location[y][card[deck.getNumber()].getRealNumber()][1]==locationAbsolute[y-13][7])||(0==locationAbsolute[y-13][7])){
                        if((location[y][card[deck.getNumber()].getRealNumber()][5]==locationAbsolute[y+1][3])||(0==locationAbsolute[y+1][3])){
                            return true;
                        }else{return false;}
            }else{return false;}
          }     
        }else if(y==90){
         if(((locationAbsolute[y-13][7])==0)&&((locationAbsolute[y-1][5])==0)){
          return false;
          }else{
            if((location[y][card[deck.getNumber()].getRealNumber()][1]==locationAbsolute[y-13][7])||(0==locationAbsolute[y-13][7])){
                    if((location[y][card[deck.getNumber()].getRealNumber()][3]==locationAbsolute[y-1][5])||(0==locationAbsolute[y-1][5])){
                            return true;
                    }else{return false;}
            }else{return false;}
          }
      }else{return false;}
    }
        
}
