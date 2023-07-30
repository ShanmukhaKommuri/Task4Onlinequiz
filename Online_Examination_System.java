import java.util.*;
import java.io.*;
import java.time.*;
class test{
  Scanner scan=new Scanner(System.in);
  void login(Candidate obj,int n)throws Exception{
    System.out.print("Enter your Roll Number : ");
    String rollNumber=scan.nextLine();
    System.out.print("Enter your password    : ");
    String password=scan.nextLine();
    for(int i=0;i<n;i++)
    {
        if(rollNumber.equals(obj.rollNumber) && password.equals(obj.password))
        {
          System.out.println("...........Test is starting...........");
          BufferedReader br=new BufferedReader(new FileReader(".\\Questions.txt"));
          BufferedReader ansf=new BufferedReader(new FileReader(".\\Answers.txt"));
          String q;
          Scanner scan=new Scanner(System.in);
          Date startTime=new Date();
          Date endTime=new Date(startTime.getTime()+120*1000);
          System.out.println("the test will end at "+endTime);
          while((q=br.readLine())!=null)
          {
            if(new Date().before(endTime)){
            System.out.println(q);
            System.out.println(br.readLine());
            String ans=scan.nextLine();
            if(ans != null){
            obj.questionsAnswered++;
            }
            if(ans.equals(ansf.readLine()))
            {
                obj.correct++;
            }
            }
            else
            {
                System.out.println("..........TIME OUT..........");
                scan.next();
                break;
            }
          }
          br.close();
          ansf.close();
          System.out.println();
          return;
        }
        else
        {
            System.out.println("\tWrong credentials.............");
        }
    }
  }
}
class Main
{
    public static void main(String args[])throws Exception{
       Scanner scan=new Scanner(System.in);
      System.out.println("");
      System.out.print("how many students are registering : ");
      int n=scan.nextInt();//if not number
      
         Candidate[] candidates=new Candidate[n];
         for(int i=0;i<n;i++)
         {
            candidates[i]=new Candidate();
            candidates[i].newUser();
         }
      
      System.out.print("Do you want to update the profile of any user (Y/N): ");
      char choice=scan.next().charAt(0);
      if(choice=='y'||choice=='Y'){
        System.out.print("Enter user Rollnumber : ");
        String updateNumber=scan.next();
        int t=0;
        for(int i=0;i<n;i++)
        {
            if(updateNumber.equals(candidates[i].rollNumber))
            {
                System.out.flush();
                candidates[i].updateUser(candidates[i]);
                t=1;
            }
        }
        if(t!=1)
        {
                System.out.println("User not found in records..............");
        }
      }
      test[] tests=new test[n];
      for(int i=0;i<n;i++){
        System.out.println("getting things ready for test..............");
        System.out.println((i+1)+"  user...........");
        tests[i]=new test();
        tests[i].login(candidates[i],n);
        System.out.println("\033[H\033[2J");
        System.out.flush();
      }
      System.out.println("\033[H\033[2J");
      System.out.flush();
      System.out.println("\t\t\tRESULTS");
      System.out.println("\t______________________________________________");
      System.out.println("SN   RollNumber\t\tName\t\tAnswered\t\tCorrect\t\tScore");
      for(int i=0;i<n;i++)
      {
        System.out.println(i+1+".)\t"+candidates[i].rollNumber+"\t\t"+candidates[i].name+"\t\t\t"+candidates[i].questionsAnswered+"\t\t\t"+candidates[i].correct+"\t\t"+candidates[i].correct*10);
      }

    }
}
class Candidate{
    String name,rollNumber,password;
    int totalMarks=0,questionsAnswered=0,unAnswered=0,correct=0,inCorrect=0;
    Scanner scan=new Scanner(System.in);
    void newUser(){
      System.out.println("\t\tWELCOME TO THE USER REGISTERATION");
      System.out.println("\t\t__________________________________");
      System.out.print("Enter user name       :  ");
      name=scan.nextLine();
      System.out.print("Enter your rollNumber :  ");
      rollNumber=scan.nextLine();
      System.out.print("Enter password        :  ");
      password=scan.nextLine();
    }
    void updateUser(Candidate ob){
      System.out.println("what do you want to update...............");
      System.out.println("1.User Name   \n2.Roll Number\n3.Password\n4.All");
      int option=scan.nextInt();
      switch(option){
         case 1:{
                System.out.print("Enter user name       :  ");
                scan.nextLine();
                ob.name=scan.nextLine();
               break;}
         case 2:System.out.print("Enter your rollNumber :  ");
                scan.nextLine();
                this.rollNumber=scan.nextLine();break;
         case 3:System.out.print("Enter password        :  ");
                scan.nextLine();
                this.password=scan.nextLine();break;
         case 4:System.out.print("Enter user name       :  ");
                scan.nextLine();
                this.name=scan.nextLine();
                System.out.println("Enter your rollNumber :  ");
                scan.nextLine();
                this.rollNumber=scan.nextLine();
                System.out.print("Enter password        :  ");
                scan.nextLine();
                this.password=scan.nextLine();break;
         default:System.out.print("Invalid Option");break;
      }
    }
}