package main

import (
	"fmt"
	"math/rand"
	"time"
)

var smokersChan = make(chan bool, 1)
var intermedChan = make(chan int, 1)

func firstSmoker() {
	for {
		item := <-intermedChan
		if item == 1 {
			fmt.Println("First smoked")
			time.Sleep(1 * time.Second)
			smokersChan <- true
		} else {
			time.Sleep(1 * time.Second)
			intermedChan <- item
		}
	}
}

func secondSmoker() {
	for {
		item := <-intermedChan
		if item == 2 {
			fmt.Println("Second smoked")
			time.Sleep(1 * time.Second)
			smokersChan <- true
		} else {
			time.Sleep(1 * time.Second)
			intermedChan <- item
		}
	}
}

func thirdSmoker() {
	for {
		item := <-intermedChan
		if item == 3 {
			fmt.Println("Third smoked")
			time.Sleep(1 * time.Second)
			smokersChan <- true
		} else {
			time.Sleep(1 * time.Second)
			intermedChan <- item
		}
	}
}

func intermediary() {
	for {
		<-smokersChan
		randInt := 1 + rand.Intn(3)
		fmt.Print("intermed ")
		fmt.Println(randInt)
		intermedChan <- randInt
	}
}

func main() {
	endChan := make(chan int)
	smokersChan <- true
	go intermediary()
	go firstSmoker()
	go secondSmoker()
	go thirdSmoker()
	<-endChan
}
