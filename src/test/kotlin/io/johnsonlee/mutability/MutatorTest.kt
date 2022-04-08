package io.johnsonlee.mutability

import kotlin.test.Test
import kotlin.test.assertNotEquals

data class ImmutableObject1(
        val value: String?
)

data class ImmutableObject2(
        val value: ImmutableObject1
)

data class ImmutableObject3(
        val value: ImmutableObject2
)

data class ImmutableObject4(
        val value: ImmutableObject3
)

data class ImmutableObject5(
        val value: ImmutableObject4
)

data class ImmutableObject6(
        val value: ImmutableObject5
)

data class ImmutableObject7(
        val value: ImmutableObject6
)

data class ImmutableObject8(
        val value: ImmutableObject7
)

data class ImmutableObject9(
        val value: ImmutableObject8
)

class MutabilityTest {

    @Test
    fun `mutate 1st level property`() {
        val origin = ImmutableObject1("Hello")
        val mutated = origin.mutate(ImmutableObject1::value) {
            "World"
        }
        assertNotEquals(origin, mutated)
    }

    @Test
    fun `mutate 2nd level property`() {
        val origin = ImmutableObject2(ImmutableObject1("Hello"))
        val mutated = origin.mutate(ImmutableObject2::value, ImmutableObject1::value) {
            "World"
        }
        assertNotEquals(origin, mutated)
    }

    @Test
    fun `mutate 3rd level property`() {
        val origin = ImmutableObject3(ImmutableObject2(ImmutableObject1("Hello")))
        val mutated = origin.mutate(ImmutableObject3::value, ImmutableObject2::value, ImmutableObject1::value) {
            "World"
        }
        assertNotEquals(origin, mutated)
    }

    @Test
    fun `mutate 4th level property`() {
        val origin = ImmutableObject4(ImmutableObject3(ImmutableObject2(ImmutableObject1("Hello"))))
        val mutated = origin.mutate(ImmutableObject4::value, ImmutableObject3::value, ImmutableObject2::value, ImmutableObject1::value) {
            "World"
        }
        assertNotEquals(origin, mutated)
    }

    @Test
    fun `mutate 5th level property`() {
        val origin = ImmutableObject5(ImmutableObject4(ImmutableObject3(ImmutableObject2(ImmutableObject1("Hello")))))
        val mutated = origin.mutate(ImmutableObject5::value, ImmutableObject4::value, ImmutableObject3::value, ImmutableObject2::value, ImmutableObject1::value) {
            "World"
        }
        assertNotEquals(origin, mutated)
    }

    @Test
    fun `mutate 6th level property`() {
        val origin = ImmutableObject6(ImmutableObject5(ImmutableObject4(ImmutableObject3(ImmutableObject2(ImmutableObject1("Hello"))))))
        val mutated = origin.mutate(ImmutableObject6::value, ImmutableObject5::value, ImmutableObject4::value, ImmutableObject3::value, ImmutableObject2::value, ImmutableObject1::value) {
            "World"
        }
        assertNotEquals(origin, mutated)
    }

    @Test
    fun `mutate 7th level property`() {
        val origin = ImmutableObject7(ImmutableObject6(ImmutableObject5(ImmutableObject4(ImmutableObject3(ImmutableObject2(ImmutableObject1("Hello")))))))
        val mutated = origin.mutate(ImmutableObject7::value, ImmutableObject6::value, ImmutableObject5::value, ImmutableObject4::value, ImmutableObject3::value, ImmutableObject2::value, ImmutableObject1::value) {
            "World"
        }
        assertNotEquals(origin, mutated)
    }

    @Test
    fun `mutate 8th level property`() {
        val origin = ImmutableObject8(ImmutableObject7(ImmutableObject6(ImmutableObject5(ImmutableObject4(ImmutableObject3(ImmutableObject2(ImmutableObject1("Hello"))))))))
        val mutated = origin.mutate(ImmutableObject8::value, ImmutableObject7::value, ImmutableObject6::value, ImmutableObject5::value, ImmutableObject4::value, ImmutableObject3::value, ImmutableObject2::value, ImmutableObject1::value) {
            "World"
        }
        assertNotEquals(origin, mutated)
    }

    @Test
    fun `mutate 9th level property`() {
        val origin = ImmutableObject9(ImmutableObject8(ImmutableObject7(ImmutableObject6(ImmutableObject5(ImmutableObject4(ImmutableObject3(ImmutableObject2(ImmutableObject1("Hello")))))))))
        val mutated = origin.mutate(ImmutableObject9::value, ImmutableObject8::value, ImmutableObject7::value, ImmutableObject6::value, ImmutableObject5::value, ImmutableObject4::value, ImmutableObject3::value, ImmutableObject2::value, ImmutableObject1::value) {
            "World"
        }
        assertNotEquals(origin, mutated)
    }

    @Test
    fun `mutate 9th level property with null`() {
        val origin = ImmutableObject9(ImmutableObject8(ImmutableObject7(ImmutableObject6(ImmutableObject5(ImmutableObject4(ImmutableObject3(ImmutableObject2(ImmutableObject1("Hello")))))))))
        val mutated = origin.mutate(ImmutableObject9::value, ImmutableObject8::value, ImmutableObject7::value, ImmutableObject6::value, ImmutableObject5::value, ImmutableObject4::value, ImmutableObject3::value, ImmutableObject2::value, ImmutableObject1::value) {
            null
        }
        assertNotEquals(origin, mutated)
    }

}