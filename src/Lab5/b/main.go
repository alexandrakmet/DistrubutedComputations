package main

import (
	"fmt"
	"math/rand"
	"sync"
	"time"
)

// Рандомізація за часом
var seed = rand.NewSource(time.Now().UnixNano())
var random = rand.New(seed)

// Зміни однієї літери рядка на іншу
func changeLetter(str *[]byte) {
	ind := random.Intn(len(*str))
	switch (*str)[ind] {
	case 'A':
		(*str)[ind] = 'C'
		break
	case 'B':
		(*str)[ind] = 'D'
		break
	case 'C':
		(*str)[ind] = 'A'
		break
	case 'D':
		(*str)[ind] = 'B'
		break
	}
}

// Зміна літер до моменту виконання умов
func routine(initStr *[]byte, wg *sync.WaitGroup, finish chan bool, again chan bool) {
	for {
		changeLetter(initStr)
		wg.Done()

		select {
		case <-finish:
			return
		case <-again:
			continue
		}
	}
}

// Підрахунок кількості літер А і В
func calc(str *[]byte) int {
	res := 0
	for _, char := range *str {
		if char == 'A' || char == 'B' {
			res++
		}
	}
	return res
}

// Перевірка того, що сума кількостей літер А і В однакова хоча б у трьох рядках
func checker(strings *[][]byte, wg *sync.WaitGroup, finish chan bool, again chan bool) {
	for {
		// Усі зупинились перед бар'єром
		wg.Wait()

		// Відповідність між числом літер А і В та кількістю рядків з таким числом літер А і В
		calcDict := make(map[int]int, len(*strings))
		for _, str := range *strings {
			if _, ok := calcDict[calc(&str)]; ok {
				calcDict[calc(&str)] += 1
			} else {
				calcDict[calc(&str)] = 1
			}
		}

		// Якщо було не менше 3 рядків з однаковим числом літер А і В
		for _, v := range calcDict {
			if v >= 3 {
				for range *strings {
					finish <- true
				}
				fmt.Println("\nБар'єр завершив свою роботу:")
				printAsStrings(strings)
				return
			}
		}

		fmt.Println("\nБар'єр було перезапущено:")
		printAsStrings(strings)
		wg.Add(len(*strings))
		for range *strings {
			again <- true
		}
	}
}

// Виведення рядків
func printAsStrings(strings *[][]byte) {
	for _, str := range *strings {
		fmt.Println(string(str))
	}
}

func main() {
	wg := &sync.WaitGroup{}

	// Канал, що засвідчує кінець роботи бар'єра
	finish := make(chan bool)

	// Канал, що засвідчує необхідність перезапуску бар'єра
	again := make(chan bool)

	// Бар'єр на чотири потоки
	wg.Add(4)

	// Рядки з літерами A, B, C, D у числовому форматі
	strings := [][]byte{[]byte("AABCD"), []byte("BABCD"), []byte("CABCD"), []byte("DABCD")}

	go routine(&strings[0], wg, finish, again)
	go routine(&strings[1], wg, finish, again)
	go routine(&strings[2], wg, finish, again)
	go routine(&strings[3], wg, finish, again)
	checker(&strings, wg, finish, again)
}
