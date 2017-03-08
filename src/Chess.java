import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chess {
	private static char[][] board;
	private static ArrayList<Integer> moves = new ArrayList<Integer>();
	private static boolean move=false;
	public static boolean color=true;
	public boolean checkmate=false;
	private static boolean white_check;
	private static boolean black_check;
	private static boolean enpassantw;
	private static boolean enpassantb;
	private static boolean enpassantnow_white;
	private static boolean enpassantnow_black;
	private static boolean random;
	private static int row=0;
	private static int col=0;
	private static int oldrow=0;
	private static int oldcol=0;
	private static int pawnrow;
	private static int pawncol;
	private static char piece;
	private static char colorcheck;

	
	
	public Chess(){
		board= new char[][] 
			  {{'R','N','B','Q','K','B','N','R'},
			   {'P','P','P','P','P','P','P','P'},
		       {'0','0','0','0','0','0','0','0'},
		       {'0','0','0','0','0','0','0','0'},
		       {'0','0','0','0','0','0','0','0'},
		       {'0','0','0','0','0','0','0','0'},
		       {'p','p','p','p','p','p','p','p'},
		       {'r','n','b','q','k','b','n','r'}};
		       };
	public boolean move (int r, int c, int r2, int c2, char piece){
		//add takebacks
		//add restart game
		row=r2;
		col=c2;
		int count=0;
		char temp;
		//while(mwhile){}
		if(color){
		if(checkmove(r,c,r2,c2,piece,false,false) && Character.isUpperCase(board[r][c])){
				checkmove(r,c,r2,c2,piece,false,true);
				temp=board[row][col];
				board[row][col]=board[r][c];
				board[r][c]='0';
				if(!checkforcheck('w',0,0,0,0)){
					oldrow=r;
					oldcol=c;
					move=true;
					count++;
					}
				board[r][c]=board[row][col];
				board[row][col]=temp;
					}
		}
		else{
			System.out.println("else statement for black");
			if(checkmove(r,c,r2,c2,piece,false,false) && Character.isLowerCase(board[r][c])){
				checkmove(r,c,row,col,piece,false,true);
				System.out.println("checkmove for black");
				temp=board[row][col];
				board[row][col]=board[r][c];
				board[r][c]='0';
				if(!checkforcheck('l',0,0,0,0)){
					System.out.println("BLACK MOVE CONFIRMED");
					oldrow=r;
					oldcol=c;
					move=true;
					count++;
					}
				board[r][c]=board[row][col];
				board[row][col]=temp;
							}
						}
		/*
		 * if(count==2){
			printboard();
			while(true){
			System.out.println("Please clarify which piece you wish to move:");
			String orig_square=scan.next().toLowerCase();
			if(orig_square.length()==3){
				int tempoldrow=Integer.parseInt(orig_square.substring(2,3))-1;
				int tempoldcol=getfile(orig_square.charAt(1));
				if(checkmove(tempoldrow,tempoldcol,row,col,m,false,false)){
					oldrow=tempoldrow;
					oldcol=tempoldcol;
					break;
				}
			}
			}
			
		}
		*/
		if(move){
			if((row==oldrow+1 || row==oldrow-1) && (col==oldcol-1 || col==oldcol+1) && (board[row][col]=='0') && Character.toLowerCase(board[oldrow][oldcol])=='p'){
				if(enpassantnow_white && board[oldrow][oldcol]=='P'){
					System.out.println("White enpassant move confirmed");
					board[row-1][col]='0';
					printboard();
					enpassantnow_white=false;
					enpassantw=false;
				}
				else if(enpassantnow_black && board[oldrow][oldcol]=='p'){
					board[row+1][col]='0';
					enpassantnow_black=false;
					enpassantb=false;
				}
				else{
					System.out.println("Please Try Again due to enpassant failiure");
					printboard();
					random=true;
				}
			}
			if(!random){
			if(color){
				colorcheck='l';
			}
			else{colorcheck='w';
			}
			moves.add(oldrow);
			moves.add(oldcol);
			moves.add(row);
			moves.add(col);
			moves.add((int)board[row][col]);
			board[row][col]=board[oldrow][oldcol];
			board[oldrow][oldcol]='0';
			color=!color;
			System.out.println("color: "+String.valueOf(color));
			if(checkforcheck(colorcheck,0,0,0,0)){
				System.out.println("check for check statement");
			if(checkformate(colorcheck)){
				System.out.println("check for mate statement");
				checkmate=true;
			}
			}
			System.out.println(colorcheck);
			System.out.println("checkmate:" + String.valueOf(checkmate));
			random=false;
			printboard();
			move=false;
			return true;
			}
			else{
				System.out.println("Please try again");
				random=false;
				printboard();
				move=false;
				return false;
			}
		}
		else{
			System.out.println("Please Try Again");
			printboard();
			random=false;
			printboard();
			move=false;
			return false;
		}
	}
	public static boolean checkmove(int oldr,int oldc,int r,int c,char piece,boolean bool_cforc,boolean real_move){
		boolean twopawnw=false;
		boolean twopawnb=false;
		boolean pawncheck=false;
		boolean pieceinway = true;
		boolean check = false;
		boolean precheck = false;
		char square=board[r][c];
		piece=Character.toLowerCase(piece);
		switch(piece){
		case 'p':
			if((color || bool_cforc) && Character.isUpperCase(board[oldr][oldc])){
				if(r==oldr+1 && c==oldc && board[r][c]=='0'){
					check = true;
				}
				else if(oldr==1 && r==3 && c==oldc && board[r][c]=='0' && board[2][c]=='0'){
					check = true;
					twopawnw=true;
					
				}
				else if(r==oldr+1 && (c==oldc-1 || c==oldc+1)){
					if(Character.isLowerCase(square)){
					check = true;
					}
					else if(r-1>-1 && Character.isLowerCase(board[r-1][c])){
						System.out.println("enpassant white qualified as true");
						System.out.println(board[r][c]);
						System.out.println(r);
						System.out.println(c);
						System.out.println(board[oldr][oldc]);
						System.out.println(oldr);
						System.out.println(oldc);
						check=true;
						if(enpassantw && real_move){
							System.out.println("enpassantw is true");
							System.out.println("r and c vs pawnrow and pawncol");
							System.out.println(r);
							System.out.println(c);
							System.out.println(pawnrow);
							System.out.println(pawncol);
							System.out.println("------------");
							pawncheck=true;
							if(r==pawnrow+1 && c==pawncol){
							System.out.println("white enpassant move checked");
							enpassantnow_white=true;
							pawnrow=0;
							pawncol=0;
							}
						}
					}
				}
			}
			else if((!color || bool_cforc) && Character.isLowerCase(board[oldr][oldc])){
				if(r==oldr-1 && c==oldc && board[r][c]=='0'){
					check = true;
				}
				else if(oldr==6 && r==4 && c==oldc) {
					System.out.println("two squares for black");
					twopawnb = true;
					check=true;
				}
				else if(r==oldr-1 && (c==oldc-1 || c==oldc+1)){
					if(Character.isUpperCase(square)){
						check = true;
						}
					else if(r+1<board.length && Character.isUpperCase(board[r+1][c])){
						check=true;
						if(enpassantb && real_move){
							pawncheck=true;
							if(r==pawnrow-1 && c==pawncol){
							enpassantnow_black=true;
							pawnrow=0;
							pawncol=0;
							}
						}
					}
				}
			}
			break;
		case 'n':
				if(((r==oldr+2 || r==oldr-2) && (c==oldc+1 || c==oldc-1)) || ((r==oldr+1 || r==oldr-1) && (c==oldc+2 || c==oldc-2))){
					System.out.println("first if statement for knight");
					if((color || bool_cforc) && !(Character.isUpperCase(square))){
							check = true;
						}
					else if((!color || bool_cforc) && !(Character.isLowerCase(square))){
						check = true;
					}
				}
				break;
		case 'b':
			if(Math.abs(r-oldr)==Math.abs(c-oldc)){
				if((color || bool_cforc) && !(Character.isUpperCase(square))){
		            precheck=true;
				}
				else if ((!color || bool_cforc) && !(Character.isLowerCase(square))){
					precheck = true;
				}
			}
			if(precheck){
				if(r-oldr>0){
	            	if(c-oldc>0){
	            		while(oldr!=r-1 && oldc!=c-1 && pieceinway){
	            			oldr++;
	            			oldc++;
	            			if(Character.isUpperCase(board[oldr][oldc]) || Character.isLowerCase(board[oldr][oldc])){
	            				pieceinway=false;
	            			}
	            		}
	            		
	            	}
	            	else if(c-oldc<0){
	            		while(oldr!=r-1 && oldc!=c+1 && pieceinway){
	            			oldr++;
	            			oldc--;
	            			if(Character.isUpperCase(board[oldr][oldc]) || Character.isLowerCase(board[oldr][oldc])){
	            				pieceinway=false;
	            			}
	            		}
	            	}
	            }
				else if(r-oldr<0){
	            	if(c-oldc>0){
	            		while(oldr!=r-1 && oldc!=c-1 && pieceinway){
	            			oldr--;
	            			oldc++;
	            			if(Character.isUpperCase(board[oldr][oldc]) || Character.isLowerCase(board[oldr][oldc])){
	            				pieceinway=false;
	            			}
	            		}
	            		
	            	}
	            	else if(c-oldc<0){
	            		while(oldr!=r-1 && oldc!=c+1 && pieceinway){
	            			oldr--;
	            			oldc--;
	            			if(Character.isUpperCase(board[oldr][oldc]) || Character.isLowerCase(board[oldr][oldc])){
	            				pieceinway=false;
	            			}
	            		}
	            	}
	            	
	            }
	        if(pieceinway){
	        	check = true;
        	}
			}
			break;
		case 'r':	
			if(r==oldr || c==oldc){
				if((color || bool_cforc) && !(Character.isUpperCase(square))){
					precheck = true;
				}
				else if ((!color || bool_cforc) && !(Character.isLowerCase(square))){
					precheck = true;
				}
			}
				if(precheck){
					if(oldr<r){
						while(oldr!=r-1){
							oldr++;
							if(Character.isUpperCase(board[oldr][oldc]) || Character.isLowerCase(board[oldr][oldc])){
								pieceinway=false;
							}
						}
					}
					else if(oldr>r){
						while(oldr!=r+1){
							oldr--;
							if(Character.isUpperCase(board[oldr][oldc]) || Character.isLowerCase(board[oldr][oldc])){
								pieceinway=false;
							}
						}
					}
					else if(oldc<c){
						while(oldc!=c-1){
							oldc++;
							if(Character.isUpperCase(board[oldr][oldc]) || Character.isLowerCase(board[oldr][oldc])){
								pieceinway=false;
							}
						}
					}
					else if(oldc>c){
						while(oldc!=c+1){
							oldc--;
							if(Character.isUpperCase(board[oldr][oldc]) || Character.isLowerCase(board[oldr][oldc])){
								pieceinway=false;
							}
						}
					}
				if(pieceinway){
					check=true;
				}
			}
			break;
		case 'q':
			if((Math.abs(r-oldr)==Math.abs(c-oldc)) || (r==oldr || c==oldc)){
				if((color || bool_cforc) && !(Character.isUpperCase(square))){
					precheck = true;
				}
				else if ((!color || bool_cforc) && !(Character.isLowerCase(square))){
					precheck = true;
				}
			}
			if(precheck){
				if(Math.abs(r-oldr)==Math.abs(c-oldc)){
					if(r-oldr>0){
		            	if(c-oldc>0){
		            		while(oldr!=r-1 && oldc!=c-1 && pieceinway){
		            			oldr++;
		            			oldc++;
		            			if(Character.isUpperCase(board[oldr][oldc]) || Character.isLowerCase(board[oldr][oldc])){
		            				pieceinway=false;
		            			}
		            		}
		            		
		            	}
		            	else if(c-oldc<0){
		            		while(oldr!=r-1 && oldc!=c+1 && pieceinway){
		            			oldr++;
		            			oldc--;
		            			if(Character.isUpperCase(board[oldr][oldc]) || Character.isLowerCase(board[oldr][oldc])){
		            				pieceinway=false;
		            			}
		            		}
		            	}
		            }
					else if(r-oldr<0){
		            	if(c-oldc>0){
		            		while(oldr!=r-1 && oldc!=c-1 && pieceinway){
		            			oldr--;
		            			oldc++;
		            			if(Character.isUpperCase(board[oldr][oldc]) || Character.isLowerCase(board[oldr][oldc])){
		            				pieceinway=false;
		            			}
		            		}
		            		
		            	}
		            	else if(c-oldc<0){
		            		while(oldr!=r-1 && oldc!=c+1 && pieceinway){
		            			oldr--;
		            			oldc--;
		            			if(Character.isUpperCase(board[oldr][oldc]) || Character.isLowerCase(board[oldr][oldc])){
		            				pieceinway=false;
		            			}
		            		}
		            	}
					}
				}
					else{
						if(oldr<r){
							while(oldr!=r-1){
								oldr++;
								if(Character.isUpperCase(board[oldr][oldc]) || Character.isLowerCase(board[oldr][oldc])){
									pieceinway=false;
								}
							}
						}
						else if(oldr>r){
							while(oldr!=r+1){
								oldr--;
								if(Character.isUpperCase(board[oldr][oldc]) || Character.isLowerCase(board[oldr][oldc])){
									pieceinway=false;
								}
							}
						}
						else if(oldc<c){
							while(oldc!=c-1){
								oldc++;
								if(Character.isUpperCase(board[oldr][oldc]) || Character.isLowerCase(board[oldr][oldc])){
									pieceinway=false;
								}
							}
						}
						else if(oldc>c){
							while(oldc!=c+1){
								oldc--;
								if(Character.isUpperCase(board[oldr][oldc]) || Character.isLowerCase(board[oldr][oldc])){
									pieceinway=false;
								}
							}
						}
					}
				if(pieceinway){
					check=true;
				}
			}
			break;
		case 'k':
			if((color || bool_cforc) && !(Character.isUpperCase(square))){
	            precheck=true;
			}
			else if ((!color || bool_cforc) && !(Character.isLowerCase(square))){
				precheck = true;
			}
			if(precheck){
			if(r-oldr<=1 && r-oldr>=-1){
				if(c-oldc<=1 && c-oldc>=-1){
					check=true;
				}
			}
			}
			break;
		default:
			System.out.println("wrong");
			return false;
	}
		if(real_move && !pawncheck){
			System.out.println("if statement for real_move");
		if(twopawnb){
			System.out.println("Setting enpassantw to true");
			enpassantw=true;
			System.out.println("Setting pawnrow and pawncol");
			System.out.println(r);
			System.out.println(c);
			pawnrow=r;
			pawncol=c;
		}
		else{
			System.out.println("Setting enpassantw to false");
			enpassantw=false;
		}
		if(twopawnw){
			System.out.println("setting enpassantb to true");
			enpassantb=true;
			pawnrow=r;
			pawncol=c;
		}
		else{
			System.out.println("setting enpassantb to false");
			enpassantb=false;
		}
		}
		if(enpassantw && pawncheck && real_move){
		System.out.println("value of check:" + String.valueOf(check));
		}
		return check;
	}
	public static boolean checkforcheck(char s, int oldr, int oldc, int r, int c){
		System.out.println("CHECKING FOR CHECK");
		boolean change = false;
		char temp='Z';
		white_check=false;
		black_check=false;
		if(r>=0 && r<8 && c>=0 && c<8){
		if(oldr+oldc+r+c!=0){
			if(checkmove(oldr,oldc,r,c,'K',false,false)){
			System.out.println("checkmove confirmed under checkmate if statement");
			printboard();
			System.out.println("------------------");
			temp=board[r][c];
			board[r][c]=board[oldr][oldc];
			board[oldr][oldc]='0';
			change = true;
			printboard();
			}
			else{
				System.out.println("ELSE STATEMENT RETURNING TRUE FOR CHECK");
				return true;
			}
		}
		}
		else{
			return true;
		}
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board[i].length;j++){
				if(!(board[i][j]=='0')){
					for(int k=0;k<board.length;k++){
						for(int l=0;l<board[k].length;l++){
							if(board[i][j]=='B' && board[k][l]=='k'){
							}
							if(checkmove(i,j,k,l,board[i][j],true,false)){
								if(board[k][l]=='K'){
									if(Character.isLowerCase(board[i][j])){
										System.out.println(board[i][j]);
										System.out.println(i);
										System.out.println(j);
										System.out.println(board[k][l]);
										System.out.println(k);
										System.out.println(l);
										System.out.println("last if statement for checkforcheck()");
										white_check = true;
									}
								}
								else if(board[k][l]=='k'){
									System.out.println("ELSE IF STATEMENT FOR CHECKFORCHECK");
									System.out.println(board[i][j]);
									System.out.println(i);
									System.out.println(j);
									System.out.println(board[k][l]);
									System.out.println(k);
									System.out.println(l);
									if(Character.isUpperCase(board[i][j])){
										System.out.println("check against black king returns true");
										black_check = true;
									}
								}
								
				
							}
						}
					}
				}
			}
		}
		if(change){
			board[oldr][oldc]=board[r][c];
			board[r][c]=temp;
		}
		if(s=='w'){
			if(white_check){
				return true;
			}
			else{
				return false;
			}
		}
		else if(s=='l'){
			if(black_check){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			if(white_check && black_check){
				return true;
			}
			else{
				return false;
			}
		}
	}
	public static boolean checkformate(char c){
		boolean bool_checkformate=false;
		int kingrow=0;
		int kingcol=0;
		int count=0;
		char color1;
		if(c=='w'){
			color1='w';
		}
		//else if(s.equals("black));
		else{
			color1='l';
		}
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board[i].length;j++){
				//board[i][j].equals(color1+"K"))
				if((board[i][j]=='K' && color1=='w') || (board[i][j]=='k' && color1=='l')){
					kingrow=i;
					kingcol=j;
				}
			}
		}
		if(checkforcheck(color1,kingrow,kingcol,kingrow+1,kingcol)){
			count++;
			System.out.println("first if statement in checkformate");
			if(checkforcheck(color1,kingrow,kingcol,kingrow+1,kingcol+1)){
				count++;
				System.out.println("second if statement in checkformate");
				if(checkforcheck(color1,kingrow,kingcol,kingrow+1,kingcol-1)){
					count++;
					System.out.println("third if statement in checkformate");
					if(checkforcheck(color1,kingrow,kingcol,kingrow,kingcol+1)){
						count++;
						System.out.println("fourth if statement in checkformate");
						if(checkforcheck(color1,kingrow,kingcol,kingrow,kingcol-1)){
							count++;
							System.out.println("fifth if statement in checkformate");
							if(checkforcheck(color1,kingrow,kingcol,kingrow-1,kingcol)){
								count++;
								System.out.println("sixth if statement in checkformate");
								if(checkforcheck(color1,kingrow,kingcol,kingrow-1,kingcol+1)){
									count++;
									if(checkforcheck(color1,kingrow,kingcol,kingrow-1,kingcol-1)){
										count++;
									}
								}
							}
						}
					}
				}
			}
		}
		if(count==8){
			System.out.println("checkformate returning true");
			bool_checkformate=true;
		}
		//The block of code under this comment is checking whether a piece can block or take the piece that is checking
		boolean can_piece_block = false;
		if(bool_checkformate){
			for(int i=0;i<board.length;i++){
				for(int j=0;j<board[i].length;j++){
					//board[i][j].substring(0,1).equals(color1)
					if(Character.isUpperCase(board[i][j]) && color1=='w'){
						for(int k=0;k<board.length;k++){
							for(int l=0;l<board[k].length;l++){
								if(checkmove(i,j,k,l,board[i][j],true,false)){
									System.out.println("move is valid under checkformate");
									if(!checkforcheck(color1,0,0,0,0)){
										System.out.println("This move stops checkmate in checkformate");
										can_piece_block = true;
									}
								}
							}
						}
					}
				}
			}
			if(can_piece_block){
				System.out.println("returning false in checkformate");
				return false;
			}
			else{
				System.out.println("returning true in checkformate");
				return true;
			}
		}
		else{
			System.out.println("returning false in checkformate");
			return false;
		}

	}
	public static void makemove(String m){
		
	}
	public static int getfile(char c){
		if(c=='a'){
			return 0;
		}
		else if(c=='b'){
			return 1;
		}
		else if(c=='c'){
			return 2;
		}
		else if(c=='d'){
			return 3;
		}
		else if(c=='e'){
			return 4;
		}
		else if(c=='f'){
			return 5;
		}
		else if(c=='g'){
			return 6;
		}
		else if(c=='h'){
			return 7;
		}
		return -1;
	}
	public static void printboard(){
		for(int i=board.length-1;i>=0;i--){
			String nextrow = Arrays.toString(board[i]).replaceAll(",","");
			System.out.println(nextrow.substring(1, nextrow.length()-1));
			}
	}
	public void restart(){
		checkmate=false;
		move=false;
		color=true;
		board= new char[][] 
				  {{'R','N','B','Q','K','B','N','R'},
				   {'P','P','P','P','P','P','P','P'},
			       {'0','0','0','0','0','0','0','0'},
			       {'0','0','0','0','0','0','0','0'},
			       {'0','0','0','0','0','0','0','0'},
			       {'0','0','0','0','0','0','0','0'},
			       {'p','p','p','p','p','p','p','p'},
			       {'r','n','b','q','k','b','n','r'}};
	}
	
	public void takeback(int nummoves){
		System.out.println(Arrays.toString(moves.toArray()));
		int size = moves.size();
		for(int i=size-1;i>size-1-nummoves*5;i-=5){
			int piece = moves.get(i);
			int col = moves.get(i-1);
			int row = moves.get(i-2);
			int oldcol = moves.get(i-3);
			int oldrow = moves.get(i-4);
			board[oldrow][oldcol]=board[row][col];
			if(piece==48){
			board[row][col]='0';
			}
			else{
				board[row][col]=(char)piece;
			}
		}
		for(int i=size-1;i>size-1-nummoves*5;i--){
			moves.remove(i);
		}
	}
}
