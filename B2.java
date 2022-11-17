import java.io.*;

class replace {

	public static void main(String[] args)throws IOException {
		
		    BufferedReader obj=new BufferedReader(new InputStreamReader(System.in));
		   int f,page=0,ch,pgf=0,n,chn=0;
		boolean flag;
		  int pages[]; 		//pgf-page faul1t
		do{
			System.out.println("Menu");
			System.out.println("1.FIFO");
			System.out.println("2.LRU");
			System.out.println("3.LFU");
			System.out.println("4.EXIT");
			System.out.println("ENTER YOUR CHOICE: ");
			 ch=Integer.parseInt(obj.readLine());
			switch(ch)
			{
			case 1:
				int pt=0;
			
			System.out.println("enter no. of frames: ");
			 f=Integer.parseInt(obj.readLine());
			int frame[]=new int[f];
			for(int i=0;i<f;i++)
				{
				frame[i]=0;
				}
			System.out.println("enter the no of pages ");
			 n=Integer.parseInt(obj.readLine());
			 pages=new int[n];
			System.out.println("enter the page no ");
			for(int j=0;j<n;j++)
			pages[j]=Integer.parseInt(obj.readLine());
			do{
			int pg=0;
			for(pg=0;pg<n;pg++)
			{
			
			page=pages[pg];
			flag=true;
			for(int j=0;j<f;j++)
			{
			if(page==frame[j])
			{
			flag=false;
			 break;
			}
			}
			if(flag)
			{
			frame[pt]=page;
			pt++;
			if(pt==f)
			pt=0;
			System.out.print("frame :");
			for(int j=0;j<f;j++)
			System.out.print(frame[j]+"   ");

			System.out.println();
			pgf++;
			}
			else
			{
			System.out.print("frame :");
			for(int j=0;j<f;j++)
			System.out.print(frame[j]+"  ");

			System.out.println();
			}
			
			chn++;
			}
			}while(chn!=n);
			System.out.println("Page fault:"+pgf);
			break;
			
			case 2: 
			int k=0;
			System.out.println("enter no. of frames: ");
			 f=Integer.parseInt(obj.readLine());
			int frame1[]=new int[f];
			int a[]=new int[f];
			int b[]=new int[f];
			for(int i=0;i<f;i++)
				{
				frame1[i]=0;
				a[i]=0;
				b[i]=0;
				}
			System.out.println("enter the no of pages ");
			 n=Integer.parseInt(obj.readLine());
			 pages=new int[n];
			System.out.println("enter the page no ");
			for(int j=0;j<n;j++)
			pages[j]=Integer.parseInt(obj.readLine());
			do{
			int pg=0;
			for(pg=0;pg<n;pg++)
			{
				page=pages[pg];
				flag=true;
				for(int j=0;j<f;j++)
					{
					if(page==frame1[j])
					{flag=false; break;}
					}
			
				for(int j=0;j<f && flag;j++)
					{
					if(frame1[j]==a[f-1])
					{k=j;
					 break;}
					}
			
				if(flag)
				{
				frame1[k]=page;
				System.out.println("frame :" );
				for(int j=0;j<f;j++)
				System.out.print(frame1[j]+"  ");
				pgf++;
				System.out.println();
				}
				else
				{
				System.out.println("frame :" );
				for(int j=0;j<f;j++)
				System.out.print(frame1[j]+"  ");
					System.out.println();
				}
			int p=1;
			b[0]=page;
			for(int j=0;j<a.length;j++)
			{
				if(page!=a[j] && p<f)
				{
				b[p]=a[j];
				p++;
				}
			}
			for(int j=0;j<f;j++)
			{
			a[j]=b[j];
			}
			chn++;
			
			}
			}while(chn!=n);
			System.out.println("Page fault:"+pgf);
			break;
			
			case 3:
			 k=0;
			pgf=0;
			int sml;
			System.out.println("enter no. of frames: ");
			 f=Integer.parseInt(obj.readLine());
			int frame2[]=new int[f];
			int cnt[]=new int [f];
			flag=true;
			
			for(int i=0;i<f;i++)
				{
				frame2[i]=0;
				cnt[i]=0;
				}
			System.out.println("enter the no of pages ");
			 n=Integer.parseInt(obj.readLine());
			 pages=new int[n];
			System.out.println("enter the page no ");
			for(int j=0;j<n;j++)
			pages[j]=Integer.parseInt(obj.readLine());
			do
			{
			int pg=0;
			for(pg=0;pg<n;pg++)
			{
				page=pages[pg];
				flag=true;
				
			
			for(int j=0;j<f;j++)
			{
				if(page==frame2[j])
				{
				flag=false;
				cnt[j]++;
				 break;
				}
			}
			if(flag)
			{
			
			sml=cnt[0];
			for(int j=0;j<f;j++)
			{
			if(cnt[j]<sml)
			{
			sml=cnt[j];
			break;
			}
			}
			for(int j=0;j<f;j++)
			{
			if(sml==cnt[j] )
		                  {
			frame2[j]=page;
			k=j;
			break;
			}
			}
			cnt[k]=1;
			System.out.print("frame :");
			for(int j=0;j<f;j++)
			{
			System.out.print(frame2[j]+"   ");
			System.out.println();
			pgf++;
			}
			}
			else
			{
			System.out.print("frame :");
			for(int j=0;j<f;j++)
			System.out.print(frame2[j]+"  ");

			System.out.println();
			
			}
			chn++;
			}	
			}while(chn!=n);
			System.out.println("Page fault:"+pgf);
			break;
			
			case 4:
			break;
			}
		     }	while(ch!=4);
		   }
		

	}
