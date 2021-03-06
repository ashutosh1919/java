/*
 *  Copyright 2020 The TensorFlow Authors. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  =======================================================================
 */

package org.tensorflow.types;

import java.util.function.Consumer;
import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.tools.Shape;
import org.tensorflow.tools.buffer.FloatDataBuffer;
import org.tensorflow.tools.buffer.layout.DataLayouts;
import org.tensorflow.tools.ndarray.FloatNdArray;
import org.tensorflow.tools.ndarray.NdArray;
import org.tensorflow.tools.ndarray.StdArrays;
import org.tensorflow.tools.ndarray.impl.dense.FloatDenseNdArray;
import org.tensorflow.types.family.TNumber;

/**
 * IEEE-754 half-precision 16-bit float tensor type.
 *
 * <p>Since there is no floating-point type that fits in 16 bits in Java, a conversion (with potentially
 * a precision loss) is required for each 32 bits value written or read on a tensor of this type from
 * the JVM. Therefore, if a lot of I/O operations are to be expected on a tensor, performances will be
 * improved by working with {@link TFloat32} or {@link TFloat64} data types whenever possible.
 *
 * <p>Also, {@code TFloat16} tensors normally perform better if they are located in GPU memory since most
 * CPUs do not support this format natively. For CPU computation on 16-bit floats, the {@link TBfloat16}
 * tensor type might be a better option.
 */
public interface TFloat16 extends FloatNdArray, TNumber {

  /** Type metadata */
  DataType<TFloat16> DTYPE = DataType.create("FLOAT16", 19, 2, TFloat16Impl::mapTensor);

  /**
   * Allocates a new tensor for storing a single float value.
   *
   * @param value float to store in the new tensor
   * @return the new tensor
   */
  static Tensor<TFloat16> scalarOf(float value) {
    return Tensor.of(DTYPE, Shape.scalar(), data -> data.setFloat(value));
  }

  /**
   * Allocates a new tensor for storing a vector of floats.
   *
   * @param values floats to store in the new tensor
   * @return the new tensor
   */
  static Tensor<TFloat16> vectorOf(float... values) {
    if (values == null) {
      throw new IllegalArgumentException();
    }
    return Tensor.of(DTYPE, Shape.of(values.length), data -> StdArrays.copyTo(data, values));
  }

  /**
   * Allocates a new tensor which is a copy of a given array of floats.
   *
   * <p>The tensor will have the same shape as the source array and its data will be copied.
   *
   * @param src the source array giving the shape and data to the new tensor
   * @return the new tensor
   */
  static Tensor<TFloat16> tensorOf(NdArray<Float> src) {
    return Tensor.of(DTYPE, src.shape(), src::copyTo);
  }

  /**
   * Allocates a new tensor of the given shape.
   *
   * @param shape shape of the tensor to allocate
   * @return the new tensor
   */
  static Tensor<TFloat16> tensorOf(Shape shape) {
    return Tensor.of(DTYPE, shape);
  }

  /**
   * Allocates a new tensor of the given shape and initialize its data.
   *
   * @param shape shape of the tensor to allocate
   * @param dataInit tensor data initializer
   * @return the new tensor
   * @throws org.tensorflow.TensorFlowException if the tensor cannot be allocated or initialized
   */
  static Tensor<TFloat16> tensorOf(Shape shape, Consumer<TFloat16> dataInit) {
    return Tensor.of(DTYPE, shape, dataInit);
  }
}

/**
 * Hidden implementation of a {@code TFloat16}
 */
class TFloat16Impl extends FloatDenseNdArray implements TFloat16 {

  static TFloat16 mapTensor(TF_Tensor nativeTensor, Shape shape) {
    return new TFloat16Impl(DataLayouts.FLOAT16.applyTo(TensorBuffers.toShorts(nativeTensor)), shape);
  }

  private TFloat16Impl(FloatDataBuffer buffer, Shape shape) {
    super(buffer, shape);
  }
}

