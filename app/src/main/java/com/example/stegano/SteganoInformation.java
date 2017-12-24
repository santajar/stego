package com.example.stegano;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

class SteganoInformation
{
	private File file;
	private File dataFile= null;
	private String starter;
	private String version;
	private byte features;
	private short compressionRatio;
	private int dataLength, temp;
	private boolean isEster= false;

	private byte byteArray[], name[], byte1, byte2;
	private int inputMarker, i, j;

	// Accessor methods
	public File getFile() { return file; }
	public int getInputMarker() { return inputMarker; }
	public File getDataFile() { return dataFile; }
	public String getVersion() { return version; }
	public byte   getFeatures() { return features; }
	public short getCompressionRatio() { return compressionRatio; }
	public int   getDataLength()	{ return dataLength; }
	public boolean  isEster() 		{ return isEster; }

	// Mutator methods
	public void setDataFile(File dataFile)
	{
            
		this.dataFile= dataFile;
                
                        
	}
	private void retrieveBytes(byte[] bytes, byte[] array, int marker)
	{
		byteArray= array;
		inputMarker= marker;

		int size= bytes.length;

		for(i=0; i< size; i++)
		{
			byte1= 0;
			for(j=6; j>=0; j-=2)
			{
				byte2= byteArray[inputMarker];
				inputMarker++;

				byte2&= 0x03;
				byte2<<= j;
				byte1|= byte2;
			}
			bytes[i]= byte1;
		}
	}



	private void readBytes(byte[] bytes, byte[] array, int marker)
	{
		byteArray= array;
		inputMarker= marker;

		int size= bytes.length;

		for(i=0; i< size; i++)
		{
			bytes[i]= byteArray[inputMarker];
			inputMarker++;
		}
	}

	private void readBytes(byte[] bytes)
	{
		int size= bytes.length;

		for(i=0; i< size; i++)
		{
			bytes[i]= byteArray[inputMarker];
			inputMarker++;
		}
	}

	public static char[] byteToCharArray(byte[] bytes)
	{
		int size= bytes.length, i;
		char []chars= new char[size];
		for(i=0; i<size; i++)
		{
			bytes[i]&= 0x7F;
			chars[i]= (char) bytes[i];
		}
		return chars;
	}

	public SteganoInformation(File file)
	{
		this.file= file;
		isEster= false;

		if(!file.exists())
		{
			starter= null;
			return;
		}
		byteArray= new byte[(int) file.length()];
		try
		{
			DataInputStream in= new DataInputStream(new FileInputStream(file));
			in.read(byteArray, 0, (int) file.length());
			in.close();
		}
		catch(Exception e)
		{
			starter= null;
			return;
		}

		// Obtain the original file length
		name= new byte[4];

		inputMarker= steganografi.OFFSET;

		retrieveBytes(name, byteArray, inputMarker);
		dataLength= 0;
		for(i=24,j=0; i>=0; i-=8,j++)
		{
			temp= name[j];
			temp&= 0x000000FF;
			temp<<= i;
			dataLength|= temp;
		}
		inputMarker= dataLength;

		if(dataLength<0 || dataLength>file.length())
		{
			starter= "Invalid";
			return;
		}
		else
			starter= "Valid";

		// Retrive the name and version information
		byte versionArray[]= new byte[3];
		readBytes(versionArray, byteArray, inputMarker);
		char []versionTemp= byteToCharArray(versionArray);
                
		char []ver= new char[4];
		for(i=0, j=0; i<4; i++)
			if(i== 1 || i== 2)	ver[i]= '.';
			else
			{
				ver[i]= versionTemp[j++];
                                
			}

		version= new String(ver);

		// Obtain the features
		name= new byte[1];
                
		readBytes(name);
                
		features= name[0];
               // System.out.print("fitur "+features);
		// Obtain the compression ratio
		readBytes(name);
		name[0]&= 0x7F;
                
		compressionRatio= name[0];

		name= new byte[4];
		readBytes(name);
		dataLength= 0;
		for(i=24,j=0; i>=0; i-=8,j++)
		{
			temp= name[j];
                        //System.out.print("\n"+"nama "+temp);
			temp&= 0x000000FF;
			temp<<= i;
			dataLength|= temp;
                        
		}
               
	}

	public boolean isValid()
	{
		if(starter.equals("Valid"))
		{	
			return true;
		}
		else
			return false;
	}
}