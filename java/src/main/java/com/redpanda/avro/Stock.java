/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.redpanda.avro;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class Stock extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -364685919862530607L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Stock\",\"namespace\":\"com.redpanda.avro\",\"fields\":[{\"name\":\"date\",\"type\":\"string\"},{\"name\":\"last\",\"type\":\"string\"},{\"name\":\"volume\",\"type\":\"float\"},{\"name\":\"open\",\"type\":\"string\"},{\"name\":\"high\",\"type\":\"string\"},{\"name\":\"low\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Stock> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Stock> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Stock> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Stock> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Stock> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Stock to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Stock from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Stock instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Stock fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.CharSequence date;
  private java.lang.CharSequence last;
  private float volume;
  private java.lang.CharSequence open;
  private java.lang.CharSequence high;
  private java.lang.CharSequence low;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Stock() {}

  /**
   * All-args constructor.
   * @param date The new value for date
   * @param last The new value for last
   * @param volume The new value for volume
   * @param open The new value for open
   * @param high The new value for high
   * @param low The new value for low
   */
  public Stock(java.lang.CharSequence date, java.lang.CharSequence last, java.lang.Float volume, java.lang.CharSequence open, java.lang.CharSequence high, java.lang.CharSequence low) {
    this.date = date;
    this.last = last;
    this.volume = volume;
    this.open = open;
    this.high = high;
    this.low = low;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return date;
    case 1: return last;
    case 2: return volume;
    case 3: return open;
    case 4: return high;
    case 5: return low;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: date = (java.lang.CharSequence)value$; break;
    case 1: last = (java.lang.CharSequence)value$; break;
    case 2: volume = (java.lang.Float)value$; break;
    case 3: open = (java.lang.CharSequence)value$; break;
    case 4: high = (java.lang.CharSequence)value$; break;
    case 5: low = (java.lang.CharSequence)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'date' field.
   * @return The value of the 'date' field.
   */
  public java.lang.CharSequence getDate() {
    return date;
  }


  /**
   * Sets the value of the 'date' field.
   * @param value the value to set.
   */
  public void setDate(java.lang.CharSequence value) {
    this.date = value;
  }

  /**
   * Gets the value of the 'last' field.
   * @return The value of the 'last' field.
   */
  public java.lang.CharSequence getLast() {
    return last;
  }


  /**
   * Sets the value of the 'last' field.
   * @param value the value to set.
   */
  public void setLast(java.lang.CharSequence value) {
    this.last = value;
  }

  /**
   * Gets the value of the 'volume' field.
   * @return The value of the 'volume' field.
   */
  public float getVolume() {
    return volume;
  }


  /**
   * Sets the value of the 'volume' field.
   * @param value the value to set.
   */
  public void setVolume(float value) {
    this.volume = value;
  }

  /**
   * Gets the value of the 'open' field.
   * @return The value of the 'open' field.
   */
  public java.lang.CharSequence getOpen() {
    return open;
  }


  /**
   * Sets the value of the 'open' field.
   * @param value the value to set.
   */
  public void setOpen(java.lang.CharSequence value) {
    this.open = value;
  }

  /**
   * Gets the value of the 'high' field.
   * @return The value of the 'high' field.
   */
  public java.lang.CharSequence getHigh() {
    return high;
  }


  /**
   * Sets the value of the 'high' field.
   * @param value the value to set.
   */
  public void setHigh(java.lang.CharSequence value) {
    this.high = value;
  }

  /**
   * Gets the value of the 'low' field.
   * @return The value of the 'low' field.
   */
  public java.lang.CharSequence getLow() {
    return low;
  }


  /**
   * Sets the value of the 'low' field.
   * @param value the value to set.
   */
  public void setLow(java.lang.CharSequence value) {
    this.low = value;
  }

  /**
   * Creates a new Stock RecordBuilder.
   * @return A new Stock RecordBuilder
   */
  public static com.redpanda.avro.Stock.Builder newBuilder() {
    return new com.redpanda.avro.Stock.Builder();
  }

  /**
   * Creates a new Stock RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Stock RecordBuilder
   */
  public static com.redpanda.avro.Stock.Builder newBuilder(com.redpanda.avro.Stock.Builder other) {
    if (other == null) {
      return new com.redpanda.avro.Stock.Builder();
    } else {
      return new com.redpanda.avro.Stock.Builder(other);
    }
  }

  /**
   * Creates a new Stock RecordBuilder by copying an existing Stock instance.
   * @param other The existing instance to copy.
   * @return A new Stock RecordBuilder
   */
  public static com.redpanda.avro.Stock.Builder newBuilder(com.redpanda.avro.Stock other) {
    if (other == null) {
      return new com.redpanda.avro.Stock.Builder();
    } else {
      return new com.redpanda.avro.Stock.Builder(other);
    }
  }

  /**
   * RecordBuilder for Stock instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Stock>
    implements org.apache.avro.data.RecordBuilder<Stock> {

    private java.lang.CharSequence date;
    private java.lang.CharSequence last;
    private float volume;
    private java.lang.CharSequence open;
    private java.lang.CharSequence high;
    private java.lang.CharSequence low;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.redpanda.avro.Stock.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.date)) {
        this.date = data().deepCopy(fields()[0].schema(), other.date);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.last)) {
        this.last = data().deepCopy(fields()[1].schema(), other.last);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.volume)) {
        this.volume = data().deepCopy(fields()[2].schema(), other.volume);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.open)) {
        this.open = data().deepCopy(fields()[3].schema(), other.open);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.high)) {
        this.high = data().deepCopy(fields()[4].schema(), other.high);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.low)) {
        this.low = data().deepCopy(fields()[5].schema(), other.low);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
    }

    /**
     * Creates a Builder by copying an existing Stock instance
     * @param other The existing instance to copy.
     */
    private Builder(com.redpanda.avro.Stock other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.date)) {
        this.date = data().deepCopy(fields()[0].schema(), other.date);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.last)) {
        this.last = data().deepCopy(fields()[1].schema(), other.last);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.volume)) {
        this.volume = data().deepCopy(fields()[2].schema(), other.volume);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.open)) {
        this.open = data().deepCopy(fields()[3].schema(), other.open);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.high)) {
        this.high = data().deepCopy(fields()[4].schema(), other.high);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.low)) {
        this.low = data().deepCopy(fields()[5].schema(), other.low);
        fieldSetFlags()[5] = true;
      }
    }

    /**
      * Gets the value of the 'date' field.
      * @return The value.
      */
    public java.lang.CharSequence getDate() {
      return date;
    }


    /**
      * Sets the value of the 'date' field.
      * @param value The value of 'date'.
      * @return This builder.
      */
    public com.redpanda.avro.Stock.Builder setDate(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.date = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'date' field has been set.
      * @return True if the 'date' field has been set, false otherwise.
      */
    public boolean hasDate() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'date' field.
      * @return This builder.
      */
    public com.redpanda.avro.Stock.Builder clearDate() {
      date = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'last' field.
      * @return The value.
      */
    public java.lang.CharSequence getLast() {
      return last;
    }


    /**
      * Sets the value of the 'last' field.
      * @param value The value of 'last'.
      * @return This builder.
      */
    public com.redpanda.avro.Stock.Builder setLast(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.last = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'last' field has been set.
      * @return True if the 'last' field has been set, false otherwise.
      */
    public boolean hasLast() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'last' field.
      * @return This builder.
      */
    public com.redpanda.avro.Stock.Builder clearLast() {
      last = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'volume' field.
      * @return The value.
      */
    public float getVolume() {
      return volume;
    }


    /**
      * Sets the value of the 'volume' field.
      * @param value The value of 'volume'.
      * @return This builder.
      */
    public com.redpanda.avro.Stock.Builder setVolume(float value) {
      validate(fields()[2], value);
      this.volume = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'volume' field has been set.
      * @return True if the 'volume' field has been set, false otherwise.
      */
    public boolean hasVolume() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'volume' field.
      * @return This builder.
      */
    public com.redpanda.avro.Stock.Builder clearVolume() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'open' field.
      * @return The value.
      */
    public java.lang.CharSequence getOpen() {
      return open;
    }


    /**
      * Sets the value of the 'open' field.
      * @param value The value of 'open'.
      * @return This builder.
      */
    public com.redpanda.avro.Stock.Builder setOpen(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.open = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'open' field has been set.
      * @return True if the 'open' field has been set, false otherwise.
      */
    public boolean hasOpen() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'open' field.
      * @return This builder.
      */
    public com.redpanda.avro.Stock.Builder clearOpen() {
      open = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'high' field.
      * @return The value.
      */
    public java.lang.CharSequence getHigh() {
      return high;
    }


    /**
      * Sets the value of the 'high' field.
      * @param value The value of 'high'.
      * @return This builder.
      */
    public com.redpanda.avro.Stock.Builder setHigh(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.high = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'high' field has been set.
      * @return True if the 'high' field has been set, false otherwise.
      */
    public boolean hasHigh() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'high' field.
      * @return This builder.
      */
    public com.redpanda.avro.Stock.Builder clearHigh() {
      high = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'low' field.
      * @return The value.
      */
    public java.lang.CharSequence getLow() {
      return low;
    }


    /**
      * Sets the value of the 'low' field.
      * @param value The value of 'low'.
      * @return This builder.
      */
    public com.redpanda.avro.Stock.Builder setLow(java.lang.CharSequence value) {
      validate(fields()[5], value);
      this.low = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'low' field has been set.
      * @return True if the 'low' field has been set, false otherwise.
      */
    public boolean hasLow() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'low' field.
      * @return This builder.
      */
    public com.redpanda.avro.Stock.Builder clearLow() {
      low = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Stock build() {
      try {
        Stock record = new Stock();
        record.date = fieldSetFlags()[0] ? this.date : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.last = fieldSetFlags()[1] ? this.last : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.volume = fieldSetFlags()[2] ? this.volume : (java.lang.Float) defaultValue(fields()[2]);
        record.open = fieldSetFlags()[3] ? this.open : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.high = fieldSetFlags()[4] ? this.high : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.low = fieldSetFlags()[5] ? this.low : (java.lang.CharSequence) defaultValue(fields()[5]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Stock>
    WRITER$ = (org.apache.avro.io.DatumWriter<Stock>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Stock>
    READER$ = (org.apache.avro.io.DatumReader<Stock>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.date);

    out.writeString(this.last);

    out.writeFloat(this.volume);

    out.writeString(this.open);

    out.writeString(this.high);

    out.writeString(this.low);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.date = in.readString(this.date instanceof Utf8 ? (Utf8)this.date : null);

      this.last = in.readString(this.last instanceof Utf8 ? (Utf8)this.last : null);

      this.volume = in.readFloat();

      this.open = in.readString(this.open instanceof Utf8 ? (Utf8)this.open : null);

      this.high = in.readString(this.high instanceof Utf8 ? (Utf8)this.high : null);

      this.low = in.readString(this.low instanceof Utf8 ? (Utf8)this.low : null);

    } else {
      for (int i = 0; i < 6; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.date = in.readString(this.date instanceof Utf8 ? (Utf8)this.date : null);
          break;

        case 1:
          this.last = in.readString(this.last instanceof Utf8 ? (Utf8)this.last : null);
          break;

        case 2:
          this.volume = in.readFloat();
          break;

        case 3:
          this.open = in.readString(this.open instanceof Utf8 ? (Utf8)this.open : null);
          break;

        case 4:
          this.high = in.readString(this.high instanceof Utf8 ? (Utf8)this.high : null);
          break;

        case 5:
          this.low = in.readString(this.low instanceof Utf8 ? (Utf8)this.low : null);
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}









