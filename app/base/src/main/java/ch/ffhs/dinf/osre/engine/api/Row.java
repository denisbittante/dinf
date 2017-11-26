/*
 * Bachelor-Thesis
 * Performance Test for pdf generators
 *
 * OpenAPI spec version: 1.0.0
 * Contact: denisbittante@gmail.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package ch.ffhs.dinf.osre.engine.api;

import java.util.Objects;

/**
 * Row
 */
public class Row {
	private String key = null;

	private String val = null;

	public Row key(String key) {
		this.key = key;
		return this;
	}

	/**
	 * Get key
	 * 
	 * @return key
	 **/
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Row val(String val) {
		this.val = val;
		return this;
	}

	/**
	 * Get val
	 * 
	 * @return val
	 **/
	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Row row = (Row) o;
		return Objects.equals(this.key, row.key) && Objects.equals(this.val, row.val);
	}

	@Override
	public int hashCode() {
		return Objects.hash(key, val);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Row {\n");

		sb.append("    key: ").append(toIndentedString(key)).append("\n");
		sb.append("    val: ").append(toIndentedString(val)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
