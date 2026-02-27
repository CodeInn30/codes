from transformers import pipeline
from sentence_transformers import SentenceTransformer, util


# ==========================================================
# Q1 & Q2 - Text Generation using GPT-2
# ==========================================================
text_generator = pipeline("text-generation", model="gpt2")

generated_output = text_generator(
    "The boy was riding a",
    max_length=40
)

print("Generator Result:",
      generated_output[0]["generated_text"])


# ==========================================================
# Q3 - Question Answering
# ==========================================================
qa_pipeline = pipeline("question-answering")

qa_output = qa_pipeline(
    question="who was going to the school",
    context="The Jack was going to the school"
)

print("Question Answering Result:",
      qa_output["answer"])


# ==========================================================
# Q4 & Q5 - Sentiment Analysis
# ==========================================================
sentiment_pipeline = pipeline("sentiment-analysis")

# Single sentence
single_sentiment = sentiment_pipeline(
    "The cpu is working to slow"
)

print("Sentiment Analysis Result:",
      single_sentiment[0]["label"],
      "-",
      single_sentiment[0]["score"])


# Multiple sentences
multiple_sentiments = sentiment_pipeline([
    "The car looks too good",
    "boy is getting hungry so they becaming angry"
])

for result in multiple_sentiments:
    print("Sentiment Analysis Result:",
          result["label"],
          "-",
          result["score"])


# ==========================================================
# Q6 & Q8 - Grammar and Spelling Correction
# ==========================================================
grammar_pipeline = pipeline(
    "text2text-generation",
    model="grammarly/coedit-large"
)

grammar_output = grammar_pipeline(
    "The boy go school"
)

print("Grammar Result:",
      grammar_output[0]["generated_text"])


spelling_pipeline = pipeline(
    "text2text-generation",
    model="vennify/t5-base-grammar-correction"
)

spelling_output = spelling_pipeline(
    "The boyy is goinng tu schol"
)

print("Spelling Result:",
      spelling_output[0]["generated_text"])


# ==========================================================
# Q7 - Zero-Shot Classification
# ==========================================================
zero_shot_classifier = pipeline("zero-shot-classification")

classification_result = zero_shot_classifier(
    "The Indian cricket team won the match by 5 wickets.",
    candidate_labels=["Sports", "Politics", "Technology"]
)

print("Label:",
      classification_result["labels"],
      "Score:",
      classification_result["scores"])


# ==========================================================
# Q9 - Semantic Similarity
# ==========================================================
similarity_model = SentenceTransformer("all-MiniLM-L6-v2")

embedding_1 = similarity_model.encode("Jack is the boy")
embedding_2 = similarity_model.encode("Zara is the girl")

similarity_score = util.cos_sim(embedding_1, embedding_2)

print("Semantic Similarity:",
      similarity_score.item())


# ==========================================================
# Q10 & Q11 - Translation (English to Hindi)
# ==========================================================
translator = pipeline(
    "translation",
    model="Helsinki-NLP/opus-mt-en-hi"
)

translation_output = translator("Hello world")

print("Translation Result:",
      translation_output[0]["translation_text"])


# ==========================================================
# Q12 - Fill Mask using BERT
# ==========================================================
fill_mask_pipeline = pipeline(
    "fill-mask",
    model="bert-base-uncased"
)

mask_predictions = fill_mask_pipeline(
    "AI is a [MASK] technology."
)

for prediction in mask_predictions:
    print(prediction["token_str"],
          prediction["score"])