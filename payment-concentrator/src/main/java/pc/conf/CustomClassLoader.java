package pc.conf;

public class CustomClassLoader extends ClassLoader {

	@Override
	public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("CLASS LOADED " + name);
		return super.loadClass(name, true);
	}

}
