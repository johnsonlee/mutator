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
 * @param p1 the property to mutate
 * @param value the value to assign
 * @return the mutated instance
 */
inline fun <reified T : Immutable, V> T.mutate(
        p1: KProperty1<T, V>,
        value: T.() -> V
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

inline fun <reified T : Immutable, reified A : Immutable, V> T.mutate(
        p1: KProperty1<T, A>,
        p2: KProperty1<A, V>,
        value: A.() -> V
): T = this.mutate(p1) {
    p1.get(this).mutate(p2) {
        value.invoke(this)
    }
}

inline fun <reified T : Immutable, reified A : Immutable, reified B : Immutable, V> T.mutate(
        p1: KProperty1<T, A>,
        p2: KProperty1<A, B>,
        p3: KProperty1<B, V>,
        value: B.() -> V
): T = this.mutate(p1) {
    p1.get(this).mutate(p2) {
        p2.get(this).mutate(p3) {
            value.invoke(this)
        }
    }
}

inline fun <reified T : Immutable, reified A : Immutable, reified B : Immutable, reified C : Immutable, V> T.mutate(
        p1: KProperty1<T, A>,
        p2: KProperty1<A, B>,
        p3: KProperty1<B, C>,
        p4: KProperty1<C, V>,
        value: C.() -> V
): T = this.mutate(p1) {
    p1.get(this).mutate(p2) {
        p2.get(this).mutate(p3) {
            p3.get(this).mutate(p4) {
                value.invoke(this)
            }
        }
    }
}

inline fun <reified T : Immutable, reified A : Immutable, reified B : Immutable, reified C : Immutable, reified D : Immutable, V> T.mutate(
        p1: KProperty1<T, A>,
        p2: KProperty1<A, B>,
        p3: KProperty1<B, C>,
        p4: KProperty1<C, D>,
        p5: KProperty1<D, V>,
        value: D.() -> V
): T = this.mutate(p1) {
    p1.get(this).mutate(p2) {
        p2.get(this).mutate(p3) {
            p3.get(this).mutate(p4) {
                p4.get(this).mutate(p5) {
                    value.invoke(this)
                }
            }
        }
    }
}

inline fun <reified T : Immutable, reified A : Immutable, reified B : Immutable, reified C : Immutable, reified D : Immutable, reified E : Immutable, V> T.mutate(
        p1: KProperty1<T, A>,
        p2: KProperty1<A, B>,
        p3: KProperty1<B, C>,
        p4: KProperty1<C, D>,
        p5: KProperty1<D, E>,
        p6: KProperty1<E, V>,
        value: E.() -> V
): T = this.mutate(p1) {
    p1.get(this).mutate(p2) {
        p2.get(this).mutate(p3) {
            p3.get(this).mutate(p4) {
                p4.get(this).mutate(p5) {
                    p5.get(this).mutate(p6) {
                        value.invoke(this)
                    }
                }
            }
        }
    }
}

inline fun <reified T : Immutable, reified A : Immutable, reified B : Immutable, reified C : Immutable, reified D : Immutable, reified E : Immutable, reified F : Immutable, V> T.mutate(
        p1: KProperty1<T, A>,
        p2: KProperty1<A, B>,
        p3: KProperty1<B, C>,
        p4: KProperty1<C, D>,
        p5: KProperty1<D, E>,
        p6: KProperty1<E, F>,
        p7: KProperty1<F, V>,
        value: F.() -> V
): T = this.mutate(p1) {
    p1.get(this).mutate(p2) {
        p2.get(this).mutate(p3) {
            p3.get(this).mutate(p4) {
                p4.get(this).mutate(p5) {
                    p5.get(this).mutate(p6) {
                        p6.get(this).mutate(p7) {
                            value.invoke(this)
                        }
                    }
                }
            }
        }
    }
}

inline fun <reified T : Immutable, reified A : Immutable, reified B : Immutable, reified C : Immutable, reified D : Immutable, reified E : Immutable, reified F : Immutable, reified G : Immutable, V> T.mutate(
        p1: KProperty1<T, A>,
        p2: KProperty1<A, B>,
        p3: KProperty1<B, C>,
        p4: KProperty1<C, D>,
        p5: KProperty1<D, E>,
        p6: KProperty1<E, F>,
        p7: KProperty1<F, G>,
        p8: KProperty1<G, V>,
        value: G.() -> V
): T = this.mutate(p1) {
    p1.get(this).mutate(p2) {
        p2.get(this).mutate(p3) {
            p3.get(this).mutate(p4) {
                p4.get(this).mutate(p5) {
                    p5.get(this).mutate(p6) {
                        p6.get(this).mutate(p7) {
                            p7.get(this).mutate(p8) {
                                value.invoke(this)
                            }
                        }
                    }
                }
            }
        }
    }
}

inline fun <reified T : Immutable, reified A : Immutable, reified B : Immutable, reified C : Immutable, reified D : Immutable, reified E : Immutable, reified F : Immutable, reified G : Immutable, reified H : Immutable, V> T.mutate(
        p1: KProperty1<T, A>,
        p2: KProperty1<A, B>,
        p3: KProperty1<B, C>,
        p4: KProperty1<C, D>,
        p5: KProperty1<D, E>,
        p6: KProperty1<E, F>,
        p7: KProperty1<F, G>,
        p8: KProperty1<G, H>,
        p9: KProperty1<H, V>,
        value: H.() -> V
): T = this.mutate(p1) {
    p1.get(this).mutate(p2) {
        p2.get(this).mutate(p3) {
            p3.get(this).mutate(p4) {
                p4.get(this).mutate(p5) {
                    p5.get(this).mutate(p6) {
                        p6.get(this).mutate(p7) {
                            p7.get(this).mutate(p8) {
                                p8.get(this).mutate(p9) {
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
