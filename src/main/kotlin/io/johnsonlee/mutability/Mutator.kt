package io.johnsonlee.mutability

import kotlin.reflect.KCallable
import kotlin.reflect.KProperty1
import kotlin.reflect.full.functions
import kotlin.reflect.full.instanceParameter
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.valueParameters

/**
 * Mutates the specified property [p1] with provided [value]
 *
 * @receiver the type to mutate
 * @param p1 the property of [T] to mutate
 * @param value the value to assign
 * @return the mutated instance
 * @throws [IllegalArgumentException] if [T] is not a data class
 */
inline fun <reified T : Any, V> T.mutate(
        p1: KProperty1<T, V?>,
        value: T.() -> V?
): T {
    require(T::class.isData) {
        "${T::class.qualifiedName} is not a data class!"
    }

    val properties = T::class.memberProperties.map {
        it.name to it
    }.toMap()
    val copy = T::class.functions.filterIsInstance<KCallable<T>>().single {
        it.name == "copy"
    }
    val arg0 = mapOf(copy.instanceParameter!! to this)
    val args = copy.valueParameters.map {
        it to when (it.name) {
            p1.name -> value.invoke(this)
            else -> properties[it.name]?.get(this)
        }
    }.toMap()
    return copy.callBy(arg0 + args)
}

/**
 * Mutates the specified property [p1]::[p2] with provided [value]
 *
 * @receiver the type to mutate
 * @param p1 the property of [T] to mutate
 * @param p2 the property of [p1] to mutate
 * @param value the value to assign
 * @return the mutated instance
 * @throws [IllegalArgumentException] if [T] is not a data class
 */
inline fun <reified T : Any, reified A : Any, V> T.mutate(
        p1: KProperty1<T, A?>,
        p2: KProperty1<A, V?>,
        value: A.() -> V?
): T = this.mutate(p1) {
    p1.get(this)?.mutate(p2) {
        value.invoke(this)
    }
}

/**
 * Mutates the specified property [p1]::[p2]::[p3] with provided [value]
 *
 * @receiver the type to mutate
 * @param p1 the property of [T] to mutate
 * @param p2 the property of [p1] to mutate
 * @param p3 the property of [p2] to mutate
 * @param value the value to assign
 * @return the mutated instance
 * @throws [IllegalArgumentException] if [T] is not a data class
 */
inline fun <reified T : Any, reified A : Any, reified B : Any, V> T.mutate(
        p1: KProperty1<T, A?>,
        p2: KProperty1<A, B?>,
        p3: KProperty1<B, V?>,
        value: B.() -> V?
): T = this.mutate(p1) {
    p1.get(this)?.mutate(p2) {
        p2.get(this)?.mutate(p3) {
            value.invoke(this)
        }
    }
}

/**
 * Mutates the specified property [p1]::[p2]::[p3]::[p4] with provided [value]
 *
 * @receiver the type to mutate
 * @param p1 the property of [T] to mutate
 * @param p2 the property of [p1] to mutate
 * @param p3 the property of [p2] to mutate
 * @param p4 the property of [p3] to mutate
 * @param value the value to assign
 * @return the mutated instance
 * @throws [IllegalArgumentException] if [T] is not a data class
 */
inline fun <reified T : Any, reified A : Any, reified B : Any, reified C : Any, V> T.mutate(
        p1: KProperty1<T, A?>,
        p2: KProperty1<A, B?>,
        p3: KProperty1<B, C?>,
        p4: KProperty1<C, V?>,
        value: C.() -> V?
): T = this.mutate(p1) {
    p1.get(this)?.mutate(p2) {
        p2.get(this)?.mutate(p3) {
            p3.get(this)?.mutate(p4) {
                value.invoke(this)
            }
        }
    }
}

/**
 * Mutates the specified property [p1]::[p2]::[p3]::[p4]::[p5] with provided [value]
 *
 * @receiver the type to mutate
 * @param p1 the property of [T] to mutate
 * @param p2 the property of [p1] to mutate
 * @param p3 the property of [p2] to mutate
 * @param p4 the property of [p3] to mutate
 * @param p5 the property of [p4] to mutate
 * @param value the value to assign
 * @return the mutated instance
 * @throws [IllegalArgumentException] if [T] is not a data class
 */
inline fun <reified T : Any, reified A : Any, reified B : Any, reified C : Any, reified D : Any, V> T.mutate(
        p1: KProperty1<T, A?>,
        p2: KProperty1<A, B?>,
        p3: KProperty1<B, C?>,
        p4: KProperty1<C, D?>,
        p5: KProperty1<D, V?>,
        value: D.() -> V?
): T = this.mutate(p1) {
    p1.get(this)?.mutate(p2) {
        p2.get(this)?.mutate(p3) {
            p3.get(this)?.mutate(p4) {
                p4.get(this)?.mutate(p5) {
                    value.invoke(this)
                }
            }
        }
    }
}

/**
 * Mutates the specified property [p1]::[p2]::[p3]::[p4]::[p5]::[p6] with provided [value]
 *
 * @receiver the type to mutate
 * @param p1 the property of [T] to mutate
 * @param p2 the property of [p1] to mutate
 * @param p3 the property of [p2] to mutate
 * @param p4 the property of [p3] to mutate
 * @param p5 the property of [p4] to mutate
 * @param p6 the property of [p5] to mutate
 * @param value the value to assign
 * @return the mutated instance
 * @throws [IllegalArgumentException] if [T] is not a data class
 */
inline fun <reified T : Any, reified A : Any, reified B : Any, reified C : Any, reified D : Any, reified E : Any, V> T.mutate(
        p1: KProperty1<T, A?>,
        p2: KProperty1<A, B?>,
        p3: KProperty1<B, C?>,
        p4: KProperty1<C, D?>,
        p5: KProperty1<D, E?>,
        p6: KProperty1<E, V?>,
        value: E.() -> V?
): T = this.mutate(p1) {
    p1.get(this)?.mutate(p2) {
        p2.get(this)?.mutate(p3) {
            p3.get(this)?.mutate(p4) {
                p4.get(this)?.mutate(p5) {
                    p5.get(this)?.mutate(p6) {
                        value.invoke(this)
                    }
                }
            }
        }
    }
}

/**
 * Mutates the specified property [p1]::[p2]::[p3]::[p4]::[p5]::[p6]::[p7] with provided [value]
 *
 * @receiver the type to mutate
 * @param p1 the property of [T] to mutate
 * @param p2 the property of [p1] to mutate
 * @param p3 the property of [p2] to mutate
 * @param p4 the property of [p3] to mutate
 * @param p5 the property of [p4] to mutate
 * @param p6 the property of [p5] to mutate
 * @param p7 the property of [p6] to mutate
 * @param value the value to assign
 * @return the mutated instance
 * @throws [IllegalArgumentException] if [T] is not a data class
 */
inline fun <reified T : Any, reified A : Any, reified B : Any, reified C : Any, reified D : Any, reified E : Any, reified F : Any, V> T.mutate(
        p1: KProperty1<T, A?>,
        p2: KProperty1<A, B?>,
        p3: KProperty1<B, C?>,
        p4: KProperty1<C, D?>,
        p5: KProperty1<D, E?>,
        p6: KProperty1<E, F?>,
        p7: KProperty1<F, V?>,
        value: F.() -> V?
): T = this.mutate(p1) {
    p1.get(this)?.mutate(p2) {
        p2.get(this)?.mutate(p3) {
            p3.get(this)?.mutate(p4) {
                p4.get(this)?.mutate(p5) {
                    p5.get(this)?.mutate(p6) {
                        p6.get(this)?.mutate(p7) {
                            value.invoke(this)
                        }
                    }
                }
            }
        }
    }
}

/**
 * Mutates the specified property [p1]::[p2]::[p3]::[p4]::[p5]::[p6]::[p7]::[p8] with provided [value]
 *
 * @receiver the type to mutate
 * @param p1 the property of [T] to mutate
 * @param p2 the property of [p1] to mutate
 * @param p3 the property of [p2] to mutate
 * @param p4 the property of [p3] to mutate
 * @param p5 the property of [p4] to mutate
 * @param p6 the property of [p5] to mutate
 * @param p7 the property of [p6] to mutate
 * @param p8 the property of [p7] to mutate
 * @param value the value to assign
 * @return the mutated instance
 * @throws [IllegalArgumentException] if [T] is not a data class
 */
inline fun <reified T : Any, reified A : Any, reified B : Any, reified C : Any, reified D : Any, reified E : Any, reified F : Any, reified G : Any, V> T.mutate(
        p1: KProperty1<T, A?>,
        p2: KProperty1<A, B?>,
        p3: KProperty1<B, C?>,
        p4: KProperty1<C, D?>,
        p5: KProperty1<D, E?>,
        p6: KProperty1<E, F?>,
        p7: KProperty1<F, G?>,
        p8: KProperty1<G, V?>,
        value: G.() -> V?
): T = this.mutate(p1) {
    p1.get(this)?.mutate(p2) {
        p2.get(this)?.mutate(p3) {
            p3.get(this)?.mutate(p4) {
                p4.get(this)?.mutate(p5) {
                    p5.get(this)?.mutate(p6) {
                        p6.get(this)?.mutate(p7) {
                            p7.get(this)?.mutate(p8) {
                                value.invoke(this)
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * Mutates the specified property [p1]::[p2]::[p3]::[p4]::[p5]::[p6]::[p7]::[p8]::[p9] with provided [value]
 *
 * @receiver the type to mutate
 * @param p1 the property of [T] to mutate
 * @param p2 the property of [p1] to mutate
 * @param p3 the property of [p2] to mutate
 * @param p4 the property of [p3] to mutate
 * @param p5 the property of [p4] to mutate
 * @param p6 the property of [p5] to mutate
 * @param p7 the property of [p6] to mutate
 * @param p8 the property of [p7] to mutate
 * @param p9 the property of [p8] to mutate
 * @param value the value to assign
 * @return the mutated instance
 * @throws [IllegalArgumentException] if [T] is not a data class
 */
inline fun <reified T : Any, reified A : Any, reified B : Any, reified C : Any, reified D : Any, reified E : Any, reified F : Any, reified G : Any, reified H : Any, V> T.mutate(
        p1: KProperty1<T, A?>,
        p2: KProperty1<A, B?>,
        p3: KProperty1<B, C?>,
        p4: KProperty1<C, D?>,
        p5: KProperty1<D, E?>,
        p6: KProperty1<E, F?>,
        p7: KProperty1<F, G?>,
        p8: KProperty1<G, H?>,
        p9: KProperty1<H, V?>,
        value: H.() -> V?
): T = this.mutate(p1) {
    p1.get(this)?.mutate(p2) {
        p2.get(this)?.mutate(p3) {
            p3.get(this)?.mutate(p4) {
                p4.get(this)?.mutate(p5) {
                    p5.get(this)?.mutate(p6) {
                        p6.get(this)?.mutate(p7) {
                            p7.get(this)?.mutate(p8) {
                                p8.get(this)?.mutate(p9) {
                                    value.invoke(this)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
