package encryption;
/**
* CS3354.005
* Group 9: Shaurya Dwivedi, Len Bucchino, Marvel Hernandez, Alan Arevalo, Mason Garcia
* Project Name: Securecrypt
* This file includes an AES algorithm that uses GCM to encrypt and decrypt files and messages.
* The AES algorithm is used to encrypt and decrypt files and messages.
* The GCM model outputs ciphertext and an authentication tag.
* The main advantage of this mode, compared to other operation modes of the algorithm, is its efficiency.
*/

//this file was written by Shaurya & len

//import java cryptographic library
import javax.crypto.SecretKey;
import java.io.*;
import javax.crypto.KeyGenerator;

//start of the algorithm
public class AES {

  //declare variables
  private static byte[][] arrayOfBytes;
  static double terminator1, terminator2;
  private static int keyEncrypt, keyEncryptK, keyEncryptR;

  /**
  * keyExpansionBox is a table used for Key Expansion
  */
  private static int keyExpansionBox[] = { 
    0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a,
    0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39,
    0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a,
    0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8,
    0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef,
    0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc,
    0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b,
    0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3,
    0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94,
    0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20,
    0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35,
    0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f,
    0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04,
    0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63,
    0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd,
    0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb 
  };

  /**
  * inverse SubByteBox is a table used in inverse Sub Bytes
  */
  private static int[] inverseSubByteBox = {
    0x52, 0x09, 0x6A, 0xD5, 0x30, 0x36, 0xA5,
    0x38, 0xBF, 0x40, 0xA3, 0x9E, 0x81, 0xF3, 0xD7, 0xFB, 0x7C, 0xE3,
    0x39, 0x82, 0x9B, 0x2F, 0xFF, 0x87, 0x34, 0x8E, 0x43, 0x44, 0xC4,
    0xDE, 0xE9, 0xCB, 0x54, 0x7B, 0x94, 0x32, 0xA6, 0xC2, 0x23, 0x3D,
    0xEE, 0x4C, 0x95, 0x0B, 0x42, 0xFA, 0xC3, 0x4E, 0x08, 0x2E, 0xA1,
    0x66, 0x28, 0xD9, 0x24, 0xB2, 0x76, 0x5B, 0xA2, 0x49, 0x6D, 0x8B,
    0xD1, 0x25, 0x72, 0xF8, 0xF6, 0x64, 0x86, 0x68, 0x98, 0x16, 0xD4,
    0xA4, 0x5C, 0xCC, 0x5D, 0x65, 0xB6, 0x92, 0x6C, 0x70, 0x48, 0x50,
    0xFD, 0xED, 0xB9, 0xDA, 0x5E, 0x15, 0x46, 0x57, 0xA7, 0x8D, 0x9D,
    0x84, 0x90, 0xD8, 0xAB, 0x00, 0x8C, 0xBC, 0xD3, 0x0A, 0xF7, 0xE4,
    0x58, 0x05, 0xB8, 0xB3, 0x45, 0x06, 0xD0, 0x2C, 0x1E, 0x8F, 0xCA,
    0x3F, 0x0F, 0x02, 0xC1, 0xAF, 0xBD, 0x03, 0x01, 0x13, 0x8A, 0x6B,
    0x3A, 0x91, 0x11, 0x41, 0x4F, 0x67, 0xDC, 0xEA, 0x97, 0xF2, 0xCF,
    0xCE, 0xF0, 0xB4, 0xE6, 0x73, 0x96, 0xAC, 0x74, 0x22, 0xE7, 0xAD,
    0x35, 0x85, 0xE2, 0xF9, 0x37, 0xE8, 0x1C, 0x75, 0xDF, 0x6E, 0x47,
    0xF1, 0x1A, 0x71, 0x1D, 0x29, 0xC5, 0x89, 0x6F, 0xB7, 0x62, 0x0E,
    0xAA, 0x18, 0xBE, 0x1B, 0xFC, 0x56, 0x3E, 0x4B, 0xC6, 0xD2, 0x79,
    0x20, 0x9A, 0xDB, 0xC0, 0xFE, 0x78, 0xCD, 0x5A, 0xF4, 0x1F, 0xDD,
    0xA8, 0x33, 0x88, 0x07, 0xC7, 0x31, 0xB1, 0x12, 0x10, 0x59, 0x27,
    0x80, 0xEC, 0x5F, 0x60, 0x51, 0x7F, 0xA9, 0x19, 0xB5, 0x4A, 0x0D,
    0x2D, 0xE5, 0x7A, 0x9F, 0x93, 0xC9, 0x9C, 0xEF, 0xA0, 0xE0, 0x3B,
    0x4D, 0xAE, 0x2A, 0xF5, 0xB0, 0xC8, 0xEB, 0xBB, 0x3C, 0x83, 0x53,
    0x99, 0x61, 0x17, 0x2B, 0x04, 0x7E, 0xBA, 0x77, 0xD6, 0x26, 0xE1,
    0x69, 0x14, 0x63, 0x55, 0x21, 0x0C, 0x7D
  };

  /**
  * SubByteBox is a table used in Sub Bytes and Key Expansion
  */
  private static int[] SubByteBox = {
    0x63, 0x7C, 0x77, 0x7B, 0xF2, 0x6B, 0x6F,
    0xC5, 0x30, 0x01, 0x67, 0x2B, 0xFE, 0xD7, 0xAB, 0x76, 0xCA, 0x82,
    0xC9, 0x7D, 0xFA, 0x59, 0x47, 0xF0, 0xAD, 0xD4, 0xA2, 0xAF, 0x9C,
    0xA4, 0x72, 0xC0, 0xB7, 0xFD, 0x93, 0x26, 0x36, 0x3F, 0xF7, 0xCC,
    0x34, 0xA5, 0xE5, 0xF1, 0x71, 0xD8, 0x31, 0x15, 0x04, 0xC7, 0x23,
    0xC3, 0x18, 0x96, 0x05, 0x9A, 0x07, 0x12, 0x80, 0xE2, 0xEB, 0x27,
    0xB2, 0x75, 0x09, 0x83, 0x2C, 0x1A, 0x1B, 0x6E, 0x5A, 0xA0, 0x52,
    0x3B, 0xD6, 0xB3, 0x29, 0xE3, 0x2F, 0x84, 0x53, 0xD1, 0x00, 0xED,
    0x20, 0xFC, 0xB1, 0x5B, 0x6A, 0xCB, 0xBE, 0x39, 0x4A, 0x4C, 0x58,
    0xCF, 0xD0, 0xEF, 0xAA, 0xFB, 0x43, 0x4D, 0x33, 0x85, 0x45, 0xF9,
    0x02, 0x7F, 0x50, 0x3C, 0x9F, 0xA8, 0x51, 0xA3, 0x40, 0x8F, 0x92,
    0x9D, 0x38, 0xF5, 0xBC, 0xB6, 0xDA, 0x21, 0x10, 0xFF, 0xF3, 0xD2,
    0xCD, 0x0C, 0x13, 0xEC, 0x5F, 0x97, 0x44, 0x17, 0xC4, 0xA7, 0x7E,
    0x3D, 0x64, 0x5D, 0x19, 0x73, 0x60, 0x81, 0x4F, 0xDC, 0x22, 0x2A,
    0x90, 0x88, 0x46, 0xEE, 0xB8, 0x14, 0xDE, 0x5E, 0x0B, 0xDB, 0xE0,
    0x32, 0x3A, 0x0A, 0x49, 0x06, 0x24, 0x5C, 0xC2, 0xD3, 0xAC, 0x62,
    0x91, 0x95, 0xE4, 0x79, 0xE7, 0xC8, 0x37, 0x6D, 0x8D, 0xD5, 0x4E,
    0xA9, 0x6C, 0x56, 0xF4, 0xEA, 0x65, 0x7A, 0xAE, 0x08, 0xBA, 0x78,
    0x25, 0x2E, 0x1C, 0xA6, 0xB4, 0xC6, 0xE8, 0xDD, 0x74, 0x1F, 0x4B,
    0xBD, 0x8B, 0x8A, 0x70, 0x3E, 0xB5, 0x66, 0x48, 0x03, 0xF6, 0x0E,
    0x61, 0x35, 0x57, 0xB9, 0x86, 0xC1, 0x1D, 0x9E, 0xE1, 0xF8, 0x98,
    0x11, 0x69, 0xD9, 0x8E, 0x94, 0x9B, 0x1E, 0x87, 0xE9, 0xCE, 0x55,
    0x28, 0xDF, 0x8C, 0xA1, 0x89, 0x0D, 0xBF, 0xE6, 0x42, 0x68, 0x41,
    0x99, 0x2D, 0x0F, 0xB0, 0x54, 0xBB, 0x16 
  };

  /**
  * This method is used to generate encoded values in byteKey-value pairs
  * @return encoded byteKey value
  */
  public static byte[] KeyGenerationForEncryption() throws Exception  {
    KeyGenerator keyGeneratorForTheEncrypt = KeyGenerator.getInstance("AES");
    keyGeneratorForTheEncrypt.init(128);
    SecretKey byteKey = keyGeneratorForTheEncrypt.generateKey();

    return byteKey.getEncoded(); //encoded byteKey value
  }

  /**
  * @return array of bytes representing the encrypted data
  */
  private static byte[] ifNotFunction(byte[] a, byte[] b) {
    byte[] out = new byte[a.length];
    for (int i = 0; i < a.length; i++) {
        out[i] = (byte) (a[i] ^ b[i]);
    }

    return out;
  }

  /**
  * This method generates sub keys for a given byteKey
  * @param byteKey the byteKey to generate
  * @return tem temp array of bytes
  */
  private static byte[][] createSubByteKey(byte[] byteKey) {
    byte[][] temporaryArray = new byte[keyEncrypt * (keyEncryptR + 1)][4];
    int i = 0;

    while (i < keyEncryptK) {
      temporaryArray[i][0] = byteKey[i * 4];
      temporaryArray[i][1] = byteKey[i * 4 + 1];
      temporaryArray[i][2] = byteKey[i * 4 + 2];
      temporaryArray[i][3] = byteKey[i * 4 + 3];
      i++;
    }
    i = keyEncryptK;

    while (i < keyEncrypt * (keyEncryptR + 1)) {
      byte[] temp = new byte[4];
      for(int k = 0;k<4;k++)
      temp[k] = temporaryArray[i-1][k];

      if (i % keyEncryptK == 0) {
        temp = SubWord(rotateWord(temp));
        temp[0] = (byte) (temp[0] ^ (keyExpansionBox[i / keyEncryptK] & 0xff));
      } else if (keyEncryptK > 6 && i % keyEncryptK == 4) {
        temp = SubWord(temp);
      }

      temporaryArray[i] = ifNotFunction(temporaryArray[i - keyEncryptK], temp);
      i++;
    }

    return temporaryArray;
  }

  /**
  * This method generates sub-words for the specified word.
  * The sub-words are used in the byteKey expansion.
  * @param word the word to generate
  * @return the generated sub-words
  */
  private static byte[] SubWord(byte[] in) {
    byte[] temporaryArray = new byte[in.length];

    for (int i = 0; i < temporaryArray.length; i++) {
      temporaryArray[i] = (byte) (SubByteBox[in[i] & 0x000000ff] & 0xff);
    }
    return temporaryArray;
  }

  /**
  * This method rotates the specified word.
  * @param word the word to rotate
  * @return the rotated word
  */
  private static byte[] rotateWord(byte[] input) {
    byte[] temporaryArray = new byte[input.length];
    temporaryArray[0] = input[1];
    temporaryArray[1] = input[2];
    temporaryArray[2] = input[3];
    temporaryArray[3] = input[0];

    return temporaryArray;
  }

  /**
  * This method encrypts the specified byte array.
  * @param data the byte array to encrypt
  * @param stateArray the stateArray to encrypt
  * @param round the number of rounds
  * @return the encrypted byte array
  */
  private static byte[][] AddRoundKey(byte[][] stateArray, byte[][] arrayOfBytes, int round) {
    byte[][] temporaryArray = new byte[stateArray.length][stateArray[0].length];

    for (int c = 0; c < keyEncrypt; c++) {
      for (int l = 0; l < 4; l++)
      temporaryArray[l][c] = (byte) (stateArray[l][c] ^ arrayOfBytes[round * keyEncrypt + c][l]);
    }

    return temporaryArray;
  }

  /**
  * This method returns the sub bytes
  * @param stateArray the stateArray to encrypt
  * @return temporaryArray the encrypted byte array
  */
  private static byte[][] SubBytes(byte[][] stateArray) {
    byte[][] temporaryArray = new byte[stateArray.length][stateArray[0].length];
    for (int row = 0; row < 4; row++) {
      for (int col = 0; col < keyEncrypt; col++) {
        temporaryArray[row][col] = (byte) (SubByteBox[(stateArray[row][col] & 0x000000ff)] & 0xff);
      }
    }
    return temporaryArray;
  }

  /**
  * This method uses multiple inverse sub bytes to traverse through the encryption
  * @param stateArray is a 2D array of bytes that is used for encryption
  * @return stateArray returns the parameter 2D array
  */
  private static byte[][] InvSubBytes(byte[][] stateArray) {
    for (int row = 0; row < 4; row++) {
      for (int col = 0; col < keyEncrypt; col++) {
        stateArray[row][col] = (byte)(inverseSubByteBox[(stateArray[row][col] & 0x000000ff)]&0xff);
      }
    }
    return stateArray;
  }

  /**
  * This method uses multiple rows to traverse through the encryption
  * @param stateArray is a 2D array of bytes that is used for encryption
  * @return stateArray returns the parameter 2D array
  */
  private static byte[][] ShiftRows(byte[][] stateArray) {
    byte[] tempByte = new byte[4];
    for (int tempInt = 1; tempInt < 4; tempInt++) {
      for (int c = 0; c < keyEncrypt; c++) {
        tempByte[c] = stateArray[tempInt][(c + tempInt) % keyEncrypt];
        for (c = 0; c < keyEncrypt; c++)
        stateArray[tempInt][c] = tempByte[c];
      }
    }

    return stateArray;
  }

  /**
  * This method uses multiple inverse rows to traverse through the encryption
  * @param stateArray is a 2D array of bytes that is used for encryption
  * @return stateArray returns the parameter 2D array
  */
  private static byte[][] InvShiftRows(byte[][] stateArray) {
    byte[] tempByte = new byte[4];
    for (int tempInt = 1; tempInt < 4; tempInt++) {
      for (int c = 0; c < keyEncrypt; c++)
      tempByte[(c + tempInt)%keyEncrypt] = stateArray[tempInt][c];
      for (int c = 0; c < keyEncrypt; c++)
      stateArray[tempInt][c] = tempByte[c];
    }
    return stateArray;
  }

  /**
  * This method uses multiple inverse columns to traverse through the encryption
  * @param stateArray is a 2D array of bytes that is used for encryption
  * @return stateArray returns the parameter 2D array
  */
  private static byte[][] InvMixColumns(byte[][] stateArray){
    int[] arraySpecialInts = new int[4];
    byte b02 = (byte)0x0e, b03 = (byte)0x0b, b04 = (byte)0x0d, b05 = (byte)0x09;
    for (int c = 0; c < 4; c++) {
      arraySpecialInts[0] = multipleBytes(b02, stateArray[0][c]) ^ multipleBytes(b03, stateArray[1][c]) ^ multipleBytes(b04,stateArray[2][c])  ^ multipleBytes(b05,stateArray[3][c]);
      arraySpecialInts[1] = multipleBytes(b05, stateArray[0][c]) ^ multipleBytes(b02, stateArray[1][c]) ^ multipleBytes(b03,stateArray[2][c])  ^ multipleBytes(b04,stateArray[3][c]);
      arraySpecialInts[2] = multipleBytes(b04, stateArray[0][c]) ^ multipleBytes(b05, stateArray[1][c]) ^ multipleBytes(b02,stateArray[2][c])  ^ multipleBytes(b03,stateArray[3][c]);
      arraySpecialInts[3] = multipleBytes(b03, stateArray[0][c]) ^ multipleBytes(b04, stateArray[1][c]) ^ multipleBytes(b05,stateArray[2][c])  ^ multipleBytes(b02,stateArray[3][c]);
      for (int i = 0; i < 4; i++) stateArray[i][c] = (byte)(arraySpecialInts[i]);
    }
    return stateArray;
  }

  /**
  * This method uses multiple columns to traverse through the encryption
  * @param stateArray is a 2D array of bytes that is used for encryption
  * @return stateArray returns the parameter 2D array
  */
  private static byte[][] MixColumns(byte[][] stateArray){
    int[] arraySpecialInts = new int[4];
        byte b02 = (byte)0x02, b03 = (byte)0x03;
        for (int c = 0; c < 4; c++) {
          arraySpecialInts[0] = multipleBytes(b02, stateArray[0][c]) ^ multipleBytes(b03, stateArray[1][c]) ^ stateArray[2][c]  ^ stateArray[3][c];
          arraySpecialInts[1] = stateArray[0][c]  ^ multipleBytes(b02, stateArray[1][c]) ^ multipleBytes(b03, stateArray[2][c]) ^ stateArray[3][c];
          arraySpecialInts[2] = stateArray[0][c]  ^ stateArray[1][c]  ^ multipleBytes(b02, stateArray[2][c]) ^ multipleBytes(b03, stateArray[3][c]);
          arraySpecialInts[3] = multipleBytes(b03, stateArray[0][c]) ^ stateArray[1][c]  ^ stateArray[2][c]  ^ multipleBytes(b02, stateArray[3][c]);
          for (int i = 0; i < 4; i++) stateArray[i][c] = (byte)(arraySpecialInts[i]);
        }
        return stateArray;
  }

  /**
  * This method uses parameters to store multiple encypted bytes
  * @param a byte object used for transfer
  * @param b byte object used for transfer
  */
  public static byte multipleBytes(byte a, byte b) {
    byte aByte = a, bByte = b, tempInt = 0, tempByte;
    while (aByte != 0) {
      if ((aByte & 1) != 0)
      tempInt = (byte) (tempInt ^ bByte);
      tempByte = (byte) (bByte & 0x80);
      bByte = (byte) (bByte << 1);
      if (tempByte != 0)
      bByte = (byte) (bByte ^ 0x1b);
      aByte = (byte) ((aByte & 0xff) >> 1);
    }
    return tempInt;
  }

  /**
  * This method encrypts the inverse block functions
  * @param in is an array of bytes
  * @return temporaryArray which stores an array of encrypted bytes
  */
  public static byte[] encryptInverse(byte[] in) {
    byte[] temporaryArray = new byte[in.length];
    byte[][] stateArray = new byte[4][keyEncrypt];

    for (int i = 0; i < in.length; i++)
      stateArray[i / 4][i % 4] = in[i%4*4+i/4];

    stateArray = AddRoundKey(stateArray, arrayOfBytes, 0);
    for (int round = 1; round < keyEncryptR; round++) {
      stateArray = SubBytes(stateArray);
      stateArray = ShiftRows(stateArray);
      stateArray = MixColumns(stateArray);
      stateArray = AddRoundKey(stateArray, arrayOfBytes, round);
    }
    stateArray = SubBytes(stateArray);
    stateArray = ShiftRows(stateArray);
    stateArray = AddRoundKey(stateArray, arrayOfBytes, keyEncryptR);

    for (int i = 0; i < temporaryArray.length; i++)
      temporaryArray[i%4*4+i/4] = stateArray[i / 4][i%4];

    return temporaryArray;
  }

  /**
  * This method decrypts the inverse block functions
  * @param in is an array of bytes
  * @return temporaryArray which stores an array of decrypted bytes
  */
  public static byte[] decryptInverse(byte[] in) {
    byte[] temporaryArray = new byte[in.length];
    byte[][] stateArray = new byte[4][keyEncrypt];

    for (int i = 0; i < in.length; i++)
      stateArray[i / 4][i % 4] = in[i%4*4+i/4];

    stateArray = AddRoundKey(stateArray, arrayOfBytes, keyEncryptR);
    for (int round = keyEncryptR-1; round >=1; round--) {
      stateArray = InvSubBytes(stateArray);
      stateArray = InvShiftRows(stateArray);
      stateArray = AddRoundKey(stateArray, arrayOfBytes, round);
      stateArray = InvMixColumns(stateArray);
    }
    stateArray = InvSubBytes(stateArray);
    stateArray = InvShiftRows(stateArray);
    stateArray = AddRoundKey(stateArray, arrayOfBytes, 0);

    for (int i = 0; i < temporaryArray.length; i++)
      temporaryArray[i%4*4+i/4] = stateArray[i / 4][i%4];

    return temporaryArray;
  }

  /**
  * This method encrypts the array of bytes using the byte key and counter
  * @param in is the array of bytes
  * @param byteKey is the array of keys for the bytes
  * @param n is the counter variable
  * @return temporaryArray the temp array of encrypted bytes
  */
  public static byte[] encrypt(byte[] in,byte[] byteKey){
    keyEncrypt = 4;
    keyEncryptK = byteKey.length/4;
    keyEncryptR = keyEncryptK + 6;
    int lengthVar=0;
    byte[] paddedByteArray = new byte[1];
    int i;
    lengthVar = 16 - in.length % 16;
    paddedByteArray = new byte[lengthVar];
    paddedByteArray[0] = (byte) 0x80;

    for (i = 1; i < lengthVar; i++)
      paddedByteArray[i] = 0;

    byte[] temporaryArray = new byte[in.length + lengthVar];
    byte[] byteBlockArray = new byte[16];

    arrayOfBytes = createSubByteKey(byteKey);

    int counter = 0;

    for (i = 0; i < in.length + lengthVar; i++) {
      if (i > 0 && i % 16 == 0) {
        byteBlockArray = encryptInverse(byteBlockArray);
        System.arraycopy(byteBlockArray, 0, temporaryArray, i - 16, byteBlockArray.length);
      }
      if (i < in.length)
        byteBlockArray[i % 16] = in[i];
      else{
        byteBlockArray[i % 16] = paddedByteArray[counter % 16];
        counter++;
      }
    }
    if(byteBlockArray.length == 16){
      byteBlockArray = encryptInverse(byteBlockArray);
      System.arraycopy(byteBlockArray, 0, temporaryArray, i - 16, byteBlockArray.length);
    }

    return temporaryArray;
  }

  /**
  * This method decrypts the array of bytes using the byte key and counter
  * @param in is the array of bytes
  * @param byteKey is the array of keys for the bytes
  * @param n is the counter variable
  * @return temporaryArray the temp array of decrypted bytes
  */
  public static byte[] decrypt(byte[] in,byte[] byteKey){
    int i;
    byte[] temporaryArray = new byte[in.length];
    byte[] byteBlockArray = new byte[16];
    keyEncrypt = 4;
    keyEncryptK = byteKey.length/4;
    keyEncryptR = keyEncryptK + 6;
    arrayOfBytes = createSubByteKey(byteKey);

    for (i = 0; i < in.length; i++) {
      if (i > 0 && i % 16 == 0) {
      byteBlockArray = decryptInverse(byteBlockArray);
      System.arraycopy(byteBlockArray, 0, temporaryArray, i - 16, byteBlockArray.length);
      }
      if (i < in.length)
      byteBlockArray[i % 16] = in[i];
    }
    byteBlockArray = decryptInverse(byteBlockArray);
    System.arraycopy(byteBlockArray, 0, temporaryArray, i - 16, byteBlockArray.length);
    temporaryArray = deletePadding(temporaryArray);
    return temporaryArray;
  }

  /**
  * This method deletes padding from the byte input and cleans the data
  * @param input is an array of bytes that need cleaning
  * @return temporaryArray an array of elements that have cleaned data
  */
  private static byte[] deletePadding(byte[] input) {
    int counter = 0;

    int i = input.length - 1;
    while (input[i] == 0) {
      counter++;
      i--;
    }

    byte[] temporaryArray = new byte[input.length - counter - 1];
    System.arraycopy(input, 0, temporaryArray, 0, temporaryArray.length);
    return temporaryArray;
  }

  /**
  * This method encrypts the array of bytes using the byte key and counter
  * @param in is the array of bytes
  * @param byteKey is the array of keys for the bytes
  * @param n is the counter variable
  * @return temporaryArray the temp array of encrypted bytes
  */
  public static byte[] encrypt(byte[] in,byte[] byteKey,int n){
    terminator1=System.nanoTime();
    keyEncrypt = 4;
    keyEncryptK = byteKey.length/4;
    keyEncryptR = n;
    int lengthVar = 0;
    byte[] paddedByteArray = new byte[1];
    int i = 0;
    lengthVar = 16 - in.length % 16;
    paddedByteArray = new byte[lengthVar];
    paddedByteArray[0] = (byte) 0x80;
    for (i = 1; i < lengthVar; i++)
    paddedByteArray[i] = 0;

    byte[] temporaryArray = new byte[in.length + lengthVar];
    byte[] byteBlockArray = new byte[16];
    arrayOfBytes = createSubByteKey(byteKey);
    int counter = 0;

    for (i = 0; i < in.length + lengthVar; i++) {
    if (i > 0 && i % 16 == 0) {
                  byteBlockArray = encryptInverse(byteBlockArray);
      System.arraycopy(byteBlockArray, 0, temporaryArray, i - 16, byteBlockArray.length);
    }
    if (i < in.length)
      byteBlockArray[i % 16] = in[i];
    else{
      byteBlockArray[i % 16] = paddedByteArray[counter % 16];
      counter++;
    }
    }

    if(byteBlockArray.length == 16){
      byteBlockArray = encryptInverse(byteBlockArray);
      System.arraycopy(byteBlockArray, 0, temporaryArray, i - 16, byteBlockArray.length);
    }
    terminator2=System.nanoTime();
    return temporaryArray;
  }

  /**
  * This method decrypts the array of bytes using the byte key and counter
  * @param in is the array of bytes
  * @param byteKey is the array of keys for the bytes
  * @param n is the counter variable
  * @return temporaryArray the temp array of decrypted bytes
  */
  public static byte[] decrypt(byte[] in, byte[] byteKey, int n){
    terminator1=System.nanoTime();
    int i;
    byte[] temporaryArray = new byte[in.length];
    byte[] byteBlockArray = new byte[16];
    keyEncrypt = 4;
    keyEncryptK = byteKey.length/4;
    keyEncryptR = n;
    arrayOfBytes = createSubByteKey(byteKey);

    for (i = 0; i < in.length; i++) {
    if (i > 0 && i % 16 == 0) {
                                  byteBlockArray = decryptInverse(byteBlockArray);
      System.arraycopy(byteBlockArray, 0, temporaryArray, i - 16, byteBlockArray.length);
    }
    if (i < in.length)
      byteBlockArray[i % 16] = in[i];
    }
    byteBlockArray = decryptInverse(byteBlockArray);
    System.arraycopy(byteBlockArray, 0, temporaryArray, i - 16, byteBlockArray.length);
    temporaryArray = deletePadding(temporaryArray);
    terminator2=System.nanoTime();
    return temporaryArray;
  }
}