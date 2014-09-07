package modelo.DAO;

public interface DAO {
	public void insertar (Object a);
	public void update (Object a);
	public void eliminar (Object a);
	public Object[] consultar (Object[] a);
}
