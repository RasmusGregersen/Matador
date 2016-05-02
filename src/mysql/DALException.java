package mysql;


public class DALException extends Exception
{
    private static final long serialVersionUID = -5490114628089339322L; // Hvad bruges det til??
    public DALException(String message) { super(message); }
    public DALException(Exception e) { super(e); }
}
