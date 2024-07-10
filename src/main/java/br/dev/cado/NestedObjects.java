package br.dev.cado;

import java.util.Objects;
import java.util.function.Supplier;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.ObjectUtils;

public class NestedObjects extends ObjectUtils {
	
	/**
	 * <p>Allows fetching the value of a nested attribute with NullPointerException handling,
	 * returning null if an exception occurs
	 * </p>
	 * <p>Example: If supplier.get() returns null, this method will also return null.
	 * </p>
	 *
	 * @param supplier value of a nested attribute (e.g. () -> object.getName())
	 *                 <p>Note: don't use lambda with method reference,
	 *                 it will generate unexpected NullPointerException</p>
	 * @return (returns null in case of NullPointerException)
	 */
	public static <T> T getOrNull(Supplier<T> supplier) {
		try {
			return supplier.get();
		} catch (NullPointerException e) {
			return null;
		}
	}
	
	/**
	 * <p>Evaluates the value of a nested attribute and returns the default value if an exception occurs
	 * </p>
	 * <p>Example: If supplier.get() returns null, this method will return defaultObj.
	 * </p>
	 *
	 * @param supplier value of a nested attribute (e.g. () -> object.getName())
	 * @return (returns defaultObj for null value or if an exception occurs)
	 * @see #getOrNull(Supplier)
	 */
	public static <T> T getOrElse(Supplier<T> supplier, T defaultObj) {
		return Objects.requireNonNullElse(getOrNull(supplier), defaultObj);
	}
	
	/**
	 * <p>Evaluates the value of a nested attribute and returns an empty string if an exception occurs
	 * </p>
	 * <p>Example: If supplier.get() returns null, this method will return an empty string.
	 * </p>
	 *
	 * @param supplier value of a nested attribute (e.g. () -> object.getName())
	 * @return (returns an empty string if the value of the nested attribute is null or if an exception occurs)
	 * @see #getOrNull(Supplier)
	 */
	public static String getOrBlank(Supplier<String> supplier) {
		var value = getOrNull(supplier);
		return value == null ? "" : value;
	}
	
	/**
	 * <p>Checking if the value is null, considers it null if an exception occurs
	 * </p>
	 * <p>Example: If supplier.get() returns null, this method will return true.
	 * </p>
	 *
	 * @param supplier value of a nested attribute (e.g. () -> object.getName())
	 * @return (returns true if the value of the nested attribute is null, false otherwise)
	 * @see #getOrNull(Supplier)
	 */
	public static <T> boolean isNull(Supplier<T> supplier) {
		return java.util.Objects.isNull(getOrNull(supplier));
	}
	
	/**
	 * <p>Checking if the value is not null, considers it null if an exception occurs
	 * </p>
	 * <p>Example: If supplier.get() returns a non-null value, this method will return true.
	 * </p>
	 *
	 * @param supplier value of a nested attribute (e.g. () -> object.getName())
	 * @return (returns true if the value of the nested attribute is not null, false otherwise)
	 * @see #getOrNull(Supplier)
	 */
	public static <T> boolean isNotNull(Supplier<T> supplier) {
		return !isNull(supplier);
	}
	
	
	/**
	 * <p>Checking if the value is blank, considers it null if an exception occurs
	 * </p>
	 * <p>Example: If supplier.get() returns a blank value, this method will return true.</p>
	 * <p>  isBlank(null)      = true</p>
	 * <p>  isBlank("")        = true</p>
	 * <p>  isBlank(" ")       = true</p>
	 * <p>  isBlank("bob")     = false</p>
	 * <p>  isBlank("  bob  ") = false</p>
	 *
	 * @param supplier value of a nested attribute (e.g. () -> object.getName())
	 * @return (returns true if the value of the nested attribute is blank, false otherwise)
	 * @see #getOrNull(Supplier)
	 */
	public static boolean isBlank(Supplier<String> supplier) {
		return StringUtils.isBlank(getOrBlank(supplier));
	}
	
	/**
	 * <p>Checking if the value is not blank, considers it null if an exception occurs
	 * </p>
	 * <p>Example: If supplier.get() returns a non-blank value, this method will return true.</p>
	 * <p>  isNotBlank(null)      = false</p>
	 * <p>  isNotBlank("")        = false</p>
	 * <p>  isNotBlank(" ")       = false</p>
	 * <p>  isNotBlank("bob")     = true</p>
	 * <p>  isNotBlank("  bob  ") = true</p>
	 *
	 * @param supplier value of a nested attribute (e.g. () -> object.getName())
	 * @return (returns true if the value of the nested attribute is not blank, false otherwise)
	 * @see #getOrNull(Supplier)
	 */
	public static boolean isNotBlank(Supplier<String> supplier) {
		return !isBlank(supplier);
	}
	
	/**
	 * <p>Checking if the value is empty, considers it null if an exception occurs
	 * </p>
	 * <p>Example: If supplier.get() returns an empty value, this method will return true.</p>
	 * <p>	Optional.isEmpty()</p>
	 * <p>	CharSequence.length() == 0</p>
	 * <p>	Array.length() == 0</p>
	 * <p>	Collection.isEmpty()</p>
	 * <p>	Map.isEmpty()</p>
	 *
	 * @param supplier value of a nested attribute (e.g. () -> object.getName())
	 * @return (returns true if the value of the nested attribute is empty, false otherwise)
	 * @see #getOrNull(Supplier)
	 */
	public static <T> boolean isEmpty(Supplier<T> supplier) {
		return ObjectUtils.isEmpty(getOrNull(supplier));
	}
	
	/**
	 * <p>Checking if the value is not empty, considers it null if an exception occurs
	 * </p>
	 * <p>Example: If supplier.get() returns a non-empty value, this method will return true.</p>
	 * <p>  Optional.isPresent()</p>
	 * <p>  CharSequence.length() != 0</p>
	 * <p>  Array.length() != 0</p>
	 * <p>  !Collection.isEmpty()</p>
	 * <p>  !Map.isEmpty()</p>
	 *
	 * @param supplier value of a nested attribute (e.g. () -> object.getName())
	 * @return (returns true if the value of the nested attribute is not empty, false otherwise)
	 * @see #getOrNull(Supplier)
	 */
	public static <T> boolean isNotEmpty(Supplier<T> supplier) {
		return !isEmpty(supplier);
	}
	
	/**
	 * <p>Checking if the value is equal in both nested attributes, considers it null if an exception occurs
	 * </p>
	 * <p>Example: If supplierOne.get() and supplierTwo.get() return the same value, this method will return true.
	 * </p>
	 *
	 * @param supplierOne value of a nested attribute (e.g. () -> object.getName())
	 * @param supplierTwo value of a nested attribute (e.g. () -> object.getName())
	 * @return (returns true if the values of the nested attributes are equal, false otherwise)
	 * @see #getOrNull(Supplier)
	 */
	public static <T> boolean isEquals(Supplier<T> supplierOne, Supplier<T> supplierTwo) {
		return java.util.Objects.equals(getOrNull(supplierOne), getOrNull(supplierTwo));
	}
	
	/**
	 * <p>Checking if the value is not equal in both nested attributes, considers it null if an exception occurs
	 * </p>
	 * <p>Example: If supplierOne.get() and supplierTwo.get() return different values, this method will return true.
	 * </p>
	 *
	 * @param supplierOne value of a nested attribute (e.g. () -> object.getName())
	 * @param supplierTwo value of a nested attribute (e.g. () -> object.getName())
	 * @return (returns true if the values of the nested attributes are not equal, false otherwise)
	 * @see #getOrNull(Supplier)
	 */
	public static <T> boolean isNotEquals(Supplier<T> supplierOne, Supplier<T> supplierTwo) {
		return !isEquals(supplierOne, supplierTwo);
	}
}
