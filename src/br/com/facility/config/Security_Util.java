package br.com.facility.config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.util.Base64;

/**
 * classe responsavel pela criptografia e descriptografia
 * Os tamanhos das chaves admitidos pelo AES são: 128, 192, ou 256 bits. 
 * (Respectivamente, 16, 24 e 32 bytes).pode não funcionar 192 e 256 bits
 * @author Status Informática
 * Alterado em: 23/05/2013
 */
public class Security_Util {
	
	private static final String key="SolutionBTO4951P";
	public  static final String ISO_8859_1 = "ISO-8859-1";
	private static final String cipherTransformation = "AES/CBC/PKCS5Padding";
	private static final String aesEncryptionAlgorithm = "AES";
 
	/**
	 * descriptografia direta
	 * @param cipherText
	 * @param key
	 * @param initialVector
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
    public  static byte[] decrypt(byte[] cipherText, byte[] key, byte [] initialVector) 
    		throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
    		InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
    {
        Cipher cipher = Cipher.getInstance(cipherTransformation);
        SecretKeySpec secretKeySpecy = new SecretKeySpec(key, aesEncryptionAlgorithm);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initialVector);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpecy, ivParameterSpec);
        cipherText = cipher.doFinal(cipherText);
        return cipherText;
    }
 
    /**
     * criptografia direta
     * @param plainText
     * @param key
     * @param initialVector
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static byte[] encrypt(byte[] plainText, byte[] key, byte [] initialVector) 
    		throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, 
    		InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
    {
        Cipher cipher = Cipher.getInstance(cipherTransformation);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, aesEncryptionAlgorithm);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initialVector);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        plainText = cipher.doFinal(plainText);
        return plainText;
    }
  
    /**
     * Para enviar via HTTP é necessário transformar o texto criptografado em Base 64,
     *  senão ocorre uma perda no caminho.O inverso também é verdadeiro para decriptografar
     * */
    
    /**
     *  Descriptografa uma string base64 codificado com a chave (AES chave de 128 bits e uma Cadeia Block Cipher)
     * @param encryptedText
     * @return
     * @throws KeyException
     * @throws GeneralSecurityException
     * @throws GeneralSecurityException
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws IOException
     */
    public static String decrypt(String encryptedText) 
    		throws KeyException, GeneralSecurityException, GeneralSecurityException, 
    		InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException{
        byte[] cipheredBytes = Base64.decode(encryptedText.getBytes(ISO_8859_1), Base64.DEFAULT);
        byte[] keyBytes = getKeyBytes();
        return new String(decrypt(cipheredBytes, keyBytes, keyBytes), ISO_8859_1);
    }
    /**
     * Criptografa texto simples usando AES chave de 128 bits e uma Cadeia Block Cipher e retorna uma string codificado base64
     * @param plainText
     * @return
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String encrypt(String plainText) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
        byte[] plainTextbytes = plainText.getBytes(ISO_8859_1);
        byte[] keyBytes = getKeyBytes();
        return Base64.encodeToString(encrypt(plainTextbytes,keyBytes, keyBytes), Base64.DEFAULT);
    }
    
    /**
     * cria chave p/operações
     * @return
     * @throws UnsupportedEncodingException
     */
    public  static byte[] getKeyBytes() throws UnsupportedEncodingException{
        byte[] keyBytes= new byte[16];
        byte[] parameterKeyBytes= key.getBytes(ISO_8859_1);
        System.arraycopy(parameterKeyBytes, 0, keyBytes, 0, Math.min(parameterKeyBytes.length, keyBytes.length));
        return keyBytes;
    }
   
}
