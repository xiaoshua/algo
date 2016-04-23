#include <cstdio>
#include <algorithm>
#include <cassert>
#include <iostream>
#include <vector>
#include <queue>

using namespace std;

typedef long long LL;

template<typename T>
class PriorityQueue {
public:
    PriorityQueue() {
        ensureCapacity(MIN_SIZE);
    }

    PriorityQueue(int size) {
        ensureCapacity(max(MIN_SIZE, size));
    }

    ~PriorityQueue() {
    }

    void push(T t) {
        ensureCapacity(++_size);
        data[_size] = t;
        siftUp(_size);
    }

    T top() {
        assert(_size > 0);
        return data[1];
    }

    void pop() {
        assert(_size > 0);
        if (--_size > 0) {
            data[1] = data[_size + 1];
            siftDown(1);
        }
    }

    void clear() {
        _size = 0;
    }

    int size() {
        return _size;
    }

private:
    vector<T> data;
    int _size = 0;
    static const int MIN_SIZE = 15;
    static const int MAX_SIZE = 1 << 29;

    void ensureCapacity(int size) {
        assert(size < MAX_SIZE && size >= 0);

        // only data[1, data.size()-1] available
        data.resize(size + 1);
    }

    void siftUp(int index) {
        while (index > 1) {
            int parentIndex = index >> 1;
            if (data[parentIndex] < data[index]) {
                swap(data[parentIndex], data[index]);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    void siftDown(int index) {
        while (true) {
            int leftChildIndex = index << 1;
            int rightChildIndex = index << 1 | 1;
            if (leftChildIndex > _size) {
                break;
            }
            //find max child node
            int maxChildIndex = leftChildIndex;
            if (rightChildIndex <= _size && data[leftChildIndex] < data[rightChildIndex]) {
                maxChildIndex = rightChildIndex;
            }
            if (data[index] < data[maxChildIndex]) {
                swap(data[index], data[maxChildIndex]);
                index = maxChildIndex;
            } else {
                break;
            }
        }
    }
};

class Dragon {
private:
    int position;
    int coins;
public:

    Dragon() { }

    Dragon(int position, int coins) : position(position), coins(coins) { }

    bool operator<(Dragon anotherDragon) const {
        return this->coins > anotherDragon.coins;
    }

    int getPosition() const {
        return position;
    }

    int getCoins() const {
        return coins;
    }
};

static const int MAX_N = (const int) (2e5 + 9);


int main() {

    ios::sync_with_stdio(false);
//both pq works well.
    PriorityQueue<Dragon> dragonPq(MAX_N);
//priority_queue<Dragon> dragonPq;


    int n;
    cin >> n;
    int coinsGet = 0;
    bool success = false;
    for (int i = 2; i <= n; ++i) {
        string cmd;
        cin >> cmd;
        int value;
        cin >> value;
        if (cmd == "d") {
            coinsGet += value;
            dragonPq.push(Dragon(i, value));
        } else {
            if (i < n) {
                while (dragonPq.size() >= max(1, value)) {
                    Dragon dragon = dragonPq.top();
                    dragonPq.pop();
                    coinsGet -= dragon.getCoins();
                }
            } else {
                success = dragonPq.size() >= value;
            }
        }
    }
    if (!success) {
        cout << -1 << endl;
    } else {
        vector<int> positions;
        while (dragonPq.size() > 0) {
            Dragon dragon = dragonPq.top();
            dragonPq.pop();
            positions.push_back(dragon.getPosition());
        }
        sort(positions.begin(), positions.end());
        cout << coinsGet << endl;
        cout << positions.size() << endl;
        for (vector<int>::iterator vit = positions.begin(); vit != positions.end(); ++vit) {
            cout << *vit << (vit + 1 == positions.end() ? '\n' : ' ');
        }
    }

    return 0;
}
