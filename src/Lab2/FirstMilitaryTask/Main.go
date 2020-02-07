package main

import (
	"fmt"
	"math/rand"
)

func main() {

	property := [5]string{"item1", "item2", "item3", "item4", "item5"}
	ivanovPetrovChannel := make(chan string)
	petrovNecheporukChannel := make(chan string)
	necheporukReady := make(chan string)

	go func(items [5]string) {
		for _, item := range items {
			fmt.Println("Иванов вынес со склада " + item)
			ivanovPetrovChannel <- item
		}
		close(ivanovPetrovChannel)
	}(property)

	go func() {
		for item := range ivanovPetrovChannel {
			fmt.Println("Петров загрузил в грузовик " + item)
			petrovNecheporukChannel <- item
		}
		close(petrovNecheporukChannel)
	}()

	go func() {
		totalAmount := 0
		for item := range petrovNecheporukChannel {
			price := rand.Intn(5000)
			totalAmount += price

			fmt.Print("Нечепорук подсчитал, что цена " + item + " - "); fmt.Print(price)
			fmt.Println(" гривен")
		}
		fmt.Print("\nРыночная стоимость всей добычи: ")
		fmt.Print(totalAmount); fmt.Println(" гривен")

		close(necheporukReady)
	}()

	<-necheporukReady
}
