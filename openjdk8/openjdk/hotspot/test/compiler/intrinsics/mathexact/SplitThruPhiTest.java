/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/*
 * @test
 * @bug 8028198
 * @summary Verify that split through phi does the right thing
 * @compile SplitThruPhiTest.java
 * @run main SplitThruPhiTest -XX:+IgnoreUnrecognizedVMOptions -XX:+UnlockExperimentalVMOptions -XX:+UseMathExactIntrinsics
 *
 */

public class SplitThruPhiTest {
  public static volatile int value = 19;
  public static int store = 0;
  public static void main(String[] args) {
    for (int i = 0; i < 150000; ++i) {
      store = runTest(value);
    }
  }

  public static int runTest(int val) {
    int result = Math.addExact(val, 1);
    int total = 0;
    for (int i = val; i < 200; i = Math.addExact(i, 1)) {
      total += i;
    }
    return total;
  }
}
