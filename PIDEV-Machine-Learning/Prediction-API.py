# sart to deploy the dbscan model

from fastapi import FastAPI,  HTTPException, Body
import pickle
from typing import Optional 
import pandas as pd
from sklearn.cluster import DBSCAN
import numpy as np
import json
from dbscan_module import DbscanClustering

app = FastAPI()
@app.get('/')
async def scoring_endpoint():
    return {"hello":"word"}