# **🧠 GENERATIVE AI – SMART CHEAT SHEET 1**

## **🔥 CORE PATTERN (Most Important)**

Almost all questions follow this structure:
```python
from transformers import pipeline

task = pipeline("TASK_NAME", model="OPTIONAL_MODEL_NAME")

result = task(INPUT)

print(result)
```
That’s it.

Everything changes only in:

1. `"TASK_NAME"`

2. `model=...`

3. Type of `INPUT`

---

# **🎯 1️⃣ TEXT GENERATION**

### **Used In:**

* Q1

* Q2

### **Pattern:**
```python
from transformers import pipeline

generator = pipeline("text-generation", model="gpt2")

output = generator("Your prompt here", max_length=40)

print(output[0]["generated_text"])
```
### **Important Parameters:**

| Parameter | Meaning |
| ----- | ----- |
| max_length | Total tokens in output |
| num_return_sequences | How many outputs you want |
| temperature | Randomness |
| do_sample | Enable randomness |

### **Remember:**

Text generation → `"text-generation"`

---

# **🎯 2️⃣ QUESTION ANSWERING**

### **Used In:**

* Q3

### **Pattern:**
```python
from transformers import pipeline

qa = pipeline("question-answering")

result = qa(  
   question="Your question",  
   context="Paragraph here"  
)

print(result["answer"])
```
### **Important:**

QA needs:

* question

* context

---

# **🎯 3️⃣ SENTIMENT ANALYSIS**

### **Used In:**

* Q4

* Q5

### **Pattern:**
```python
from transformers import pipeline

sentiment = pipeline("sentiment-analysis")

result = sentiment("Your sentence")

print(result[0]["label"])  
print(result[0]["score"])

### **For Multiple Sentences:**

sentences = ["Sentence 1", "Sentence 2"]

results = sentiment(sentences)

for r in results:  
   print(r["label"], r["score"])
```
### **Output Labels:**

* POSITIVE

* NEGATIVE

---

# **🎯 4️⃣ TEXT-TO-TEXT TASKS (Grammar / Spelling)**

### **Used In:**

* Q6

* Q8

### **Pattern:**
```python
from transformers import pipeline

model = pipeline("text2text-generation", model="MODEL_NAME")

output = model("Input text here")

print(output[0]["generated_text"])
```
### **Examples of Models:**

* Grammar: `"grammarly/coedit-large"`

* T5 correction: `"vennify/t5-base-grammar-correction"`

### **Remember:**

If task transforms text → `"text2text-generation"`

---

# **🎯 5️⃣ ZERO-SHOT CLASSIFICATION**

### **Used In:**

* Q7

### **Pattern:**
```python
from transformers import pipeline

classifier = pipeline("zero-shot-classification")

result = classifier(  
   "Text here",  
   candidate_labels=["Label1", "Label2"]  
)

print(result["labels"])  
print(result["scores"])
```
### **Key Idea:**

You provide labels manually.

---

# **🎯 6️⃣ SEMANTIC SIMILARITY (Sentence Transformers)**

⚠️ This one does NOT use pipeline.

### **Used In:**

* Q9

### **Pattern:**
```python
from sentence_transformers import SentenceTransformer, util

model = SentenceTransformer("all-MiniLM-L6-v2")

emb1 = model.encode("Sentence 1")  
emb2 = model.encode("Sentence 2")

similarity = util.cos_sim(emb1, emb2)

print(similarity.item())
```
### **Concept:**

* Convert text → embeddings

* Compare using cosine similarity

---

# **🎯 7️⃣ TRANSLATION**

### **Used In:**

* Q10

* Q11

### **Pattern:**
```python
from transformers import pipeline

translator = pipeline(  
   "translation",  
   model="Helsinki-NLP/opus-mt-en-hi"  
)

result = translator("Hello world")

print(result[0]["translation_text"])
```
### **Language Model Format:**

Helsinki-NLP/opus-mt-SOURCE-TARGET

Examples:

* en-hi

* en-mr

* en-fr

---

# **🎯 8️⃣ FILL-MASK (BERT)**

### **Used In:**

* Q12

### **Pattern:**
```python
from transformers import pipeline

fill_mask = pipeline("fill-mask", model="bert-base-uncased")

output = fill_mask("AI is a [MASK] technology.")

for pred in output:  
   print(pred["token_str"], pred["score"])
```
### **Important:**

Use `[MASK]` token.

---

# **🧩 MASTER TABLE (Ultra Important)**

| Task | Pipeline Name |
| ----- | ----- |
| Text Generation | `"text-generation"` |
| Question Answering | `"question-answering"` |
| Sentiment | `"sentiment-analysis"` |
| Grammar/Correction | `"text2text-generation"` |
| Zero-shot | `"zero-shot-classification"` |
| Translation | `"translation"` |
| Fill Mask | `"fill-mask"` |

If you remember this table → you can solve 80% of exam.

---

# **⚙️ INTERNAL WORKING (High-Level Understanding)**

When you write:

pipeline("text-generation")

Behind the scenes:

1. It downloads a pretrained model

2. Downloads tokenizer

3. Converts text → tokens

4. Model predicts next tokens

5. Tokens → converted back to text

Everything is:

Text → Tokenization → Model → Prediction → Decode → Text  
---

# **🎓 EXAM STRATEGY**

Instead of memorizing 12 programs:

### **Step 1:**

Identify the task type.

### **Step 2:**

Choose correct pipeline.

### **Step 3:**

Pass correct input format.

That’s it.

---

# **🚀 BONUS: ONE UNIVERSAL TEMPLATE**

If stuck in exam:
```python
from transformers import pipeline

task = pipeline("TASK_NAME")

input_data = "Your input"

result = task(input_data)

print(result)
```
Then modify according to question.

---

# **💡 How You Should Practice**

Since you want to become strong in AI engineering:

1. Change model names

2. Change parameters

3. Test multiple inputs

4. Try max_length, temperature

5. Try different translation languages

This builds understanding, not just assignment solving.
